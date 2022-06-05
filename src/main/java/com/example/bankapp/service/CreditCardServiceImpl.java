package com.example.bankapp.service;

import com.example.bankapp.converter.CreditCardConverter;
import com.example.bankapp.dto.response.GetCreditCardActivitiesResponseDTO;
import com.example.bankapp.dto.response.GetCreditCardResponseDTO;
import com.example.bankapp.entity.*;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.helper.UserHelper;
import com.example.bankapp.repository.CardRepository;
import com.example.bankapp.repository.CreditCardRepository;
import com.example.bankapp.repository.CustomerRepository;
import com.example.bankapp.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardConverter creditCardConverter;
    private final CustomerRepository customerRepository;
    private final CreditCardRepository creditCardRepository;
    private final CardRepository cardRepository;
    private final UserHelper userHelper;
    private final PaymentRepository paymentRepository;

    @Override
    public void create(Long customerId) {
        Customer creditCardCustomer = customerRepository.findById(customerId).orElseThrow(
                () -> new BusinessServiceOperationException.CreateCreditCardException("Create credit card failed")
        );
        User creditCardUser = creditCardCustomer.getUser();
        User loggedInUser = userHelper.getLoggedInUser();
        Card card = cardRepository.findCreditCardByCustomerId(customerId);
        if(card != null) {
            throw new BusinessServiceOperationException.CreateCreditCardException("Create credit card failed");
        }
        if(creditCardCustomer.getUser() == loggedInUser || userHelper.isLoggedInUserAdmin()){
            CreditCard creditCard = creditCardConverter.toCreditCard(customerId);
            creditCardRepository.save(creditCard);
            log.info("Create credit card successfully -> {}",creditCard.getId());
            return;
        }
        else {
            throw new BusinessServiceOperationException.CreateCreditCardException("Create credit card failed");
        }

    }

    @Override
    public GetCreditCardResponseDTO getCreditCardByCustomerId(Long customerId) {
        CreditCard creditCard = creditCardRepository.findByCustomerId(customerId).orElseThrow(
                ()-> new BusinessServiceOperationException.CreditCardNotFoundException("Credit card not found")
        );
        User loggedInUser = userHelper.getLoggedInUser();
        if( !(creditCard.getCard().getCustomer().getUser() == loggedInUser) &  !(userHelper.isLoggedInUserAdmin()) ){
            throw new BusinessServiceOperationException.GetCreditCardFailedException("Get credit card failed");
        }
        GetCreditCardResponseDTO getCreditCardResponseDTO = creditCardConverter.toGetCreditCardResponseDto(creditCard);
        return getCreditCardResponseDTO;
    }

    @Override
    public List<GetCreditCardActivitiesResponseDTO> getCreditCardActivitiesByCardNo(String cardNo) {
       Card card = cardRepository.findByCardNo(cardNo).orElseThrow(
               () -> new BusinessServiceOperationException.CardNotFoundException("Card Not Found")
       );
       User loggedInUser = userHelper.getLoggedInUser();
        if( !(card.getCustomer().getUser() == loggedInUser) &  !(userHelper.isLoggedInUserAdmin()) ){
            throw new BusinessServiceOperationException.GetCreditCardFailedException("Get credit card failed");
        }
        List<Payment> payments = paymentRepository.getPaymentsByCardNo(cardNo);
        List<GetCreditCardActivitiesResponseDTO> creditCardActivities = new ArrayList<>();
        for(int i = 0; i < payments.size(); i++ ){
            Payment payment = payments.get(i);

            GetCreditCardActivitiesResponseDTO getCreditCardActivitiesResponseDTO = new GetCreditCardActivitiesResponseDTO(
                    payment.getReceiverIban(),
                    payment.getAmount(),
                    payment.getCurrency(),
                    payment.getTransferDate()
            );
            creditCardActivities.add(getCreditCardActivitiesResponseDTO);

        }
        return creditCardActivities;
    }
}
