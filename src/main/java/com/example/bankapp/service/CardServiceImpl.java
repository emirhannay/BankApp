package com.example.bankapp.service;

import com.example.bankapp.converter.CardConverter;
import com.example.bankapp.dto.response.GetAccountResponseDTO;
import com.example.bankapp.dto.response.GetCardResponseDTO;
import com.example.bankapp.entity.Card;
import com.example.bankapp.entity.Customer;
import com.example.bankapp.entity.User;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.helper.UserHelper;
import com.example.bankapp.repository.CardRepository;
import com.example.bankapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final UserHelper userHelper;
    private final CustomerRepository customerRepository;
    private final CardRepository cardRepository;
    private final CardConverter cardConverter;

    @Override
    public List<GetCardResponseDTO> getAllCards() {


        return cardRepository.findAll().stream().map(cardConverter::toGetCardResponseDTO).toList();
    }

    @Override
    public List<GetCardResponseDTO> getCardsByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found with tis id")
        );
        User loggedInUser = userHelper.getLoggedInUser();
        if( !(customer.getUser() == loggedInUser) &  !(userHelper.isLoggedInUserAdmin()) ){

            throw new BusinessServiceOperationException.GetCreditCardFailedException("Get credit card failed");
        }

        List<GetCardResponseDTO> cards = cardRepository.getCardsByCustomerId(customerId).stream().map(cardConverter::toGetCardResponseDTO).toList();
        return cards;
    }
}
