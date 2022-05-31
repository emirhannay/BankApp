package com.example.bankapp.config;

import com.example.bankapp.entity.Role;
import com.example.bankapp.entity.enums.Currency;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.repository.*;
import com.example.bankapp.entity.DrawingAccount;
import com.example.bankapp.entity.Customer;
import com.example.bankapp.entity.User;
import com.example.bankapp.entity.enums.UserStatus;
import com.example.bankapp.entity.enums.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class InitializerConfig {

    private final RoleRepository roleRepository;

    @PostConstruct
    public void onInit() {
        Role role = new Role();
        role.setName("ADMIN");
        Role role2 = new Role();
        role2.setName("CUSTOMER");
        roleRepository.save(role);
        roleRepository.save(role2);

    }
}
