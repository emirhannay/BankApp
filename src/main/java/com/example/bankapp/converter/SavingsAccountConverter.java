package com.example.bankapp.converter;

import com.example.bankapp.core.utils.CreatorService;
import com.example.bankapp.dto.request.CreateSavingsAccountRequestDTO;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Customer;
import com.example.bankapp.entity.SavingsAccount;
import com.example.bankapp.entity.enums.AccountType;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.helper.UserHelper;
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
    private final UserHelper userHelper;

    public SavingsAccount toSavingsAccount(CreateSavingsAccountRequestDTO createSavingsAccountRequestDTO){
        Customer customer = userHelper.getLoggedInCustomer();
        SavingsAccount savingsAccount = new SavingsAccount();
        Account account = new Account();
        account.setBalance(BigDecimal.ZERO);
        account.setCurrency(createSavingsAccountRequestDTO.currency());
        account.setIban(creatorService.createIban());
        account.setCreatedAt(new Date());
        account.setDeleted(false);
        account.setCustomer(customer);
        account.setAccountType(AccountType.SAVINGS);
        savingsAccount.setAccount(account);
        return savingsAccount;
    }
}
