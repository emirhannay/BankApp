package com.example.bankapp.service;

import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Customer;
import com.example.bankapp.entity.Transfer;
import com.example.bankapp.entity.enums.AccountType;
import com.example.bankapp.entity.enums.Currency;
import com.example.bankapp.exception.BaseException;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.helper.UserHelper;
import com.example.bankapp.model.ExchangeRateApiData;
import com.example.bankapp.model.SendMoneyRequest;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final UserHelper userHelper;
    private final AccountRepository accountRepository;
    private final ExchangeRateApiAdaptor exchangeRateApiAdaptor;
    private final TransferRepository transferRepository;

    @Override
    @Transactional(rollbackOn = {BaseException.class, SQLException.class})
    public void sendMoney(SendMoneyRequest sendMoneyRequest) {
        Customer loggedInCustomer = userHelper.getLoggedInCustomer();
        Account senderAccount = accountRepository.findByIban(sendMoneyRequest.senderIban())
                .orElseThrow(
                        ()-> new BusinessServiceOperationException.AccountNotFoundException("Sender account not found")
                );
        Account receiverAccount = accountRepository.findByIban(sendMoneyRequest.receiverIban())
                .orElseThrow(
                        () -> new BusinessServiceOperationException.AccountNotFoundException("Receiver account not found")
                );
        BigDecimal moneyToBeSent = sendMoneyRequest.amount();
        if( receiverAccount.getAccountType() == AccountType.SAVINGS ){
            throw new BusinessServiceOperationException
                    .MoneyTransferFailedException("You cannot send money to savings account.But you can deposit money.");
        }
        if( !(senderAccount.getCustomer() == loggedInCustomer)) {
            throw new BusinessServiceOperationException
                    .MoneyTransferFailedException("You cannot send money from an account that does not belong to you.");
        }
        if(senderAccount == receiverAccount){
            throw new BusinessServiceOperationException
                    .MoneyTransferFailedException("Sender account and receiver account cannot be the same");
        }
        if( !(doesAccountHasEnoughMoneyForTransfer(senderAccount,sendMoneyRequest)) ){
            throw new BusinessServiceOperationException
                    .MoneyTransferFailedException("There is not enough money in your account.");
        }
        if( areAccountsSameCurrencyType(senderAccount,receiverAccount) ){
            senderAccount.setBalance(senderAccount.getBalance().subtract(sendMoneyRequest.amount()));
            receiverAccount.setBalance(receiverAccount.getBalance().add(sendMoneyRequest.amount()));
        }
        else {
            senderAccount.setBalance(senderAccount.getBalance().subtract(moneyToBeSent));
            moneyToBeSent = moneyToBeSent.multiply(getExchangeRate(senderAccount,receiverAccount));
            receiverAccount.setBalance(receiverAccount.getBalance().add(moneyToBeSent));
        }
        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);
        Transfer transfer = new Transfer();
        transfer.setTransferDate(new Date());
        transfer.setSenderIban(senderAccount.getIban());
        transfer.setReceiverIban(receiverAccount.getIban());
        transfer.setAmount(moneyToBeSent);
        transfer.setCurrency(receiverAccount.getCurrency());
        transferRepository.save(transfer);


    }

    public boolean doesAccountHasEnoughMoneyForTransfer(Account senderAccount,SendMoneyRequest sendMoneyRequest){
        BigDecimal senderAccountBalance = senderAccount.getBalance();
        BigDecimal moneyToBeSent = sendMoneyRequest.amount();
        //compareTo returns -1 if balance is not enough
        if(senderAccountBalance.compareTo(moneyToBeSent) == -1){
            return false;
        }
        else {
            return true;
        }
    }
    public boolean areAccountsSameCurrencyType(Account senderAccount,Account receiverAccount){
        if(senderAccount.getCurrency() == receiverAccount.getCurrency()){
            return true;
        }
        else{
            return false;
        }
    }
    public BigDecimal getExchangeRate(Account senderAccount,Account receiverAccount){
        ExchangeRateApiData exchangeRateApiData = exchangeRateApiAdaptor.getExchangeRates(senderAccount.getCurrency());
        if(receiverAccount.getCurrency() == Currency.TRY){
            return exchangeRateApiData.getRates().getTl();
        }
        if(receiverAccount.getCurrency() == Currency.EUR){
            return exchangeRateApiData.getRates().getEur();
        }
        if(receiverAccount.getCurrency() == Currency.USD){
            return exchangeRateApiData.getRates().getUsd();
        }
        return BigDecimal.ZERO;
    }

}
