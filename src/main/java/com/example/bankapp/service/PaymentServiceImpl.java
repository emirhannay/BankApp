package com.example.bankapp.service;

import com.example.bankapp.converter.PaymentConverter;
import com.example.bankapp.dto.request.PayRequestDTO;
import com.example.bankapp.entity.*;
import com.example.bankapp.entity.enums.CardType;
import com.example.bankapp.exception.BaseException;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final PaymentConverter paymentConverter;
    private final CustomerRepository customerRepository;
    private final ExchangeRateApiAdaptor exchangeRateApiAdaptor;
    private final CardRepository cardRepository;
    private final BankCardRepository bankCardRepository;
    private final DrawingAccountRepository drawingAccountRepository;
    private final AccountRepository accountRepository;
    private final CreditCardRepository creditCardRepository;

    @Override
    @Transactional(rollbackOn = {BaseException.class, SQLException.class})
    public void pay(PayRequestDTO payRequestDTO) {
        //Verify card information
        Card card = cardRepository.findByCardNo(payRequestDTO.senderCardNo()).orElseThrow(
                () -> new BusinessServiceOperationException.CardNotFoundException("Payment failed. Please enter correct card information.")
        );
        if( !(card.getCvv().equals(payRequestDTO.cvv())) ){
            throw new BusinessServiceOperationException.CardNotFoundException("Payment failed. Please enter correct card information.");
        }
        Account receiverAccount = accountRepository.getCorporateAccountByIban(payRequestDTO.receiverIban()).orElseThrow(
                () -> new BusinessServiceOperationException.AccountNotFoundException("Payment failed. Please enter correct receiver info.")
        );

        if(card.getCardType() == CardType.BANK){

            BankCard bankCard = bankCardRepository.findByCardId(card.getId()).orElseThrow(
                    ()-> new BusinessServiceOperationException.CardNotFoundException("Payment failed.")
            );
            Account senderAccount = accountRepository.getAccountByBankCardId(bankCard.getId()).orElseThrow(
                    ()-> new BusinessServiceOperationException.DrawingAccountExistsException("Payment failed.")
            );

            int doesTheSenderAccEnoughBalance = senderAccount.getBalance().compareTo(payRequestDTO.amount());
            if(doesTheSenderAccEnoughBalance == -1){
                throw new BusinessServiceOperationException.PaymentFailedException("Payment failed. You do not have enough balance.");
            }
            else {
                BigDecimal moneyToBeSent = payRequestDTO.amount().multiply(exchangeRateApiAdaptor.getExchangeRateForTransfer(senderAccount,receiverAccount));
                senderAccount.setBalance(senderAccount.getBalance().subtract(payRequestDTO.amount()));
                receiverAccount.setBalance(receiverAccount.getBalance().add(moneyToBeSent));
                accountRepository.save(senderAccount);
                accountRepository.save(receiverAccount);
                Payment payment = paymentConverter.toPayment(payRequestDTO);
                paymentRepository.save(payment);
                log.info("Payment was successful.");
            }
        }
        else {
            CreditCard creditCard = creditCardRepository.findByCardId(card.getId()).orElseThrow(
                    ()-> new BusinessServiceOperationException.CardNotFoundException("Payment failed.")
            );
            int doesTheCreditCardEnoughBalance = creditCard.getAvailableLimit().compareTo(payRequestDTO.amount());

            if(doesTheCreditCardEnoughBalance == -1){
                throw new BusinessServiceOperationException.PaymentFailedException("Payment failed. You do not have enough balance.");
            }
            else {
                BigDecimal moneyToBeSent = payRequestDTO.amount().multiply(exchangeRateApiAdaptor.getTlExchangeRateForTransfer(receiverAccount));
                creditCard.setAvailableLimit(creditCard.getAvailableLimit().subtract(moneyToBeSent));
                creditCard.setCurrentDebt(creditCard.getCurrentDebt().subtract(moneyToBeSent));
                receiverAccount.setBalance(receiverAccount.getBalance().add(moneyToBeSent));
                Payment payment = paymentConverter.toPayment(payRequestDTO);
                accountRepository.save(receiverAccount);
                creditCardRepository.save(creditCard);
                paymentRepository.save(payment);
                log.info("Payment was successfull" );
            }

        }

    }
}
