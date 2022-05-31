package com.example.bankapp.converter;

import com.example.bankapp.core.utils.CreatorService;
import com.example.bankapp.entity.Card;
import com.example.bankapp.entity.CreditCard;
import com.example.bankapp.entity.Customer;
import com.example.bankapp.entity.enums.CardType;
import com.example.bankapp.entity.enums.CreditCardStatus;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CreditCardConverter {
    private final CustomerRepository customerRepository;
    private final CreatorService creatorService;

    public CreditCard toCreditCard(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found")
        );
        CreditCard creditCard = new CreditCard();
        Card card = new Card();
        creditCard.setCreditCardStatus(CreditCardStatus.ACTIVE);
        creditCard.setCardLimit(customer.getMonthlyEarning());
        creditCard.setCurrentDebt(BigDecimal.ZERO);
        creditCard.setAvailableLimit(customer.getMonthlyEarning());
        creditCard.setCutoffDate(creatorService.createCutOffDate());
        creditCard.setNextCutoffDate(creatorService.createNextCutOffDate());
        creditCard.setPaymentDueDate(creatorService.createPaymentDueDate());
        creditCard.setNextPaymentDueDate(creatorService.createNextPaymentDueDate());
        creditCard.setLastOutstandingBalance(BigDecimal.ZERO);
        card.setCardNo(creatorService.createCardNo());
        card.setCustomer(customer);
        card.setCvv(creatorService.createCvv());
        card.setExpirationDate(creatorService.createExpirationDate());
        card.setCardType(CardType.CREDIT);
        creditCard.setCard(card);
        return creditCard;
    }
}
