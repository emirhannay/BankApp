package com.example.bankapp.helper;

import com.example.bankapp.entity.Customer;
import com.example.bankapp.entity.Role;
import com.example.bankapp.entity.User;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.repository.CustomerRepository;
import com.example.bankapp.repository.RoleRepository;
import com.example.bankapp.repository.UserRepository;
import com.example.bankapp.security.UserDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class UserHelper {

    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

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
        List<Role> roles = getLoggedInUser().getRoles();
        for(int i = 0; i < roles.size(); i++){
               Role role = roles.get(i);
               if(role.getName().equals("ADMIN")){
                   return true;
               }
        }
        return false;
    }

}
