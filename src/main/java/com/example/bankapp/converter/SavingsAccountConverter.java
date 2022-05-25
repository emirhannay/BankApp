package com.example.bankapp.converter;

import com.example.bankapp.core.utils.CreatorService;
import com.example.bankapp.dto.request.CreateSavingsAccountRequestDTO;
import com.example.bankapp.entity.Customer;
import com.example.bankapp.entity.SavingsAccount;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.repository.CustomerRepository;
import com.example.bankapp.security.UserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class SavingsAccountConverter {

    private final CustomerRepository customerRepository;
    private final CreatorService creatorService;

    public SavingsAccount toSavingsAccount(CreateSavingsAccountRequestDTO createSavingsAccountRequestDTO){
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerRepository.findById(userDetail.getId()).orElseThrow(
                ()-> new BusinessServiceOperationException.CustomerNotFoundException("Customer Not Found")
        );
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setBalance(BigDecimal.ZERO);
        savingsAccount.setCurrency(createSavingsAccountRequestDTO.currency());
        savingsAccount.setCreatedAt(new Date());
        savingsAccount.setIban(creatorService.createIban());
        savingsAccount.setDeleted(false);
        savingsAccount.setCustomer(customer);

        return savingsAccount;
    }
}
