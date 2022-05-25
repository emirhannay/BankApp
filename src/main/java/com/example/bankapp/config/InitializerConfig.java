package com.example.bankapp.config;

import com.example.bankapp.entity.enums.Currency;
import com.example.bankapp.repository.DrawingAccountRepository;
import com.example.bankapp.entity.DrawingAccount;
import com.example.bankapp.entity.Customer;
import com.example.bankapp.entity.User;
import com.example.bankapp.entity.enums.UserStatus;
import com.example.bankapp.entity.enums.UserType;
import com.example.bankapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class InitializerConfig {

    private final CustomerRepository customerRepository;
    private final DrawingAccountRepository drawingAccountRepository;

    @PostConstruct
    public void onInit() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Customer customer = new Customer();
        User user = new User();
        user.setEmail("emir@hotmail.com");
        user.setPassword("12345");
        user.setUserStatus(UserStatus.ACTIVE);
        user.setUserType(UserType.CUSTOMER);
        DrawingAccount drawingAccount = new DrawingAccount();
        drawingAccount.setIban("TR1515156165561");
        drawingAccount.setCustomer(customer);
        drawingAccount.setCurrency(Currency.TRY);
        customerRepository.save(customer);
        drawingAccountRepository.save(drawingAccount);

    }
}
