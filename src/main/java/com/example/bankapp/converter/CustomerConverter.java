package com.example.bankapp.converter;

import com.example.bankapp.dto.request.CreateCustomerRequestDTO;
import com.example.bankapp.entity.enums.UserType;
import com.example.bankapp.entity.Customer;
import com.example.bankapp.entity.User;
import com.example.bankapp.entity.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerConverter {


    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public Customer ToCustomer(CreateCustomerRequestDTO createCustomerRequestDTO){
        Customer customer = new Customer();
        User user = new User();
        String encodedPassword = bCryptPasswordEncoder.encode(createCustomerRequestDTO.password());
        user.setEmail(createCustomerRequestDTO.email());
        user.setPassword(encodedPassword);
        user.setUserStatus(UserStatus.ACTIVE);
        user.setUserType(UserType.CUSTOMER);
        customer.setUser(user);
        customer.setIdentityNo(createCustomerRequestDTO.identityNo());


        return customer;
    }

}
