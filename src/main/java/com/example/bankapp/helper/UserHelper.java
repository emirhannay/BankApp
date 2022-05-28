package com.example.bankapp.helper;

import com.example.bankapp.entity.Customer;
import com.example.bankapp.entity.User;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.repository.CustomerRepository;
import com.example.bankapp.repository.UserRepository;
import com.example.bankapp.security.UserDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserHelper {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public Customer getLoggedInCustomer(){
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerRepository.findByUserId(userDetail.getId()).orElseThrow(
                ()-> new BusinessServiceOperationException.CustomerNotFoundException("Customer Not Found")
        );
            return customer;
    }
    public UserDetail getLoggedInUserDetail(){
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetail;
    }
    public User getLoggedInUser(){
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(userDetail.getId()).orElseThrow(
                ()-> new BusinessServiceOperationException.CustomerNotFoundException("User Not Found")
        );
        return user;
    }
    public boolean isLoggedInUserAdmin(){
        boolean isTheLoggedInUserAdmin = getLoggedInUser().getRoles().contains("ADMIN");
        return isTheLoggedInUserAdmin;
    }


}
