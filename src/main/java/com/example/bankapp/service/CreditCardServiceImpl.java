package com.example.bankapp.service;

import com.example.bankapp.converter.CreditCardConverter;
import com.example.bankapp.converter.CustomerConverter;
import com.example.bankapp.entity.Card;
import com.example.bankapp.entity.CreditCard;
import com.example.bankapp.entity.Customer;
import com.example.bankapp.entity.User;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.helper.UserHelper;
import com.example.bankapp.repository.CardRepository;
import com.example.bankapp.repository.CreditCardRepository;
import com.example.bankapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardConverter creditCardConverter;
    private final CustomerRepository customerRepository;
    private final CreditCardRepository creditCardRepository;
    private final CardRepository cardRepository;
    private final UserHelper userHelper;

    @Override
    public void create(Long customerId) {
        Customer creditCardCustomer = customerRepository.findById(customerId).orElseThrow(
                () -> new BusinessServiceOperationException.CreateCreditCardException("Create credit card failed")
        );
        User creditCardUser = creditCardCustomer.getUser();
        User loggedInUser = userHelper.getLoggedInUser();
        Card card = cardRepository.findByCustomerId(customerId);
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
}
