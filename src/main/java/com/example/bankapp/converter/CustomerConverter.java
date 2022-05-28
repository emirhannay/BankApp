package com.example.bankapp.converter;

import com.example.bankapp.dto.request.CreateCustomerRequestDTO;
import com.example.bankapp.dto.request.UpdateCustomerRequestDTO;
import com.example.bankapp.entity.Role;
import com.example.bankapp.entity.enums.UserType;
import com.example.bankapp.entity.Customer;
import com.example.bankapp.entity.User;
import com.example.bankapp.entity.enums.UserStatus;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.helper.UserHelper;
import com.example.bankapp.repository.CustomerRepository;
import com.example.bankapp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class CustomerConverter {


    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final UserHelper userHelper;
    private final CustomerRepository customerRepository;
    private final RoleRepository roleRepository;

    public Customer toCustomer(CreateCustomerRequestDTO createCustomerRequestDTO){
        Customer customer = new Customer();
        User user = new User();
        Role role = roleRepository.findByName("CUSTOMER").orElseThrow(
                () -> new BusinessServiceOperationException.CreateCustomerFailedException("Create customer failed")
        );
        String encodedPassword = bCryptPasswordEncoder.encode(createCustomerRequestDTO.password());
        user.setEmail(createCustomerRequestDTO.email());
        user.setPassword(encodedPassword);
        user.setUserStatus(UserStatus.ACTIVE);
        user.setUserType(UserType.CUSTOMER);
        user.setName(createCustomerRequestDTO.name());
        user.setPhoneNumber(createCustomerRequestDTO.phoneNumber());
        user.getRoles().add(role);
        customer.setUser(user);
        customer.setIdentityNo(createCustomerRequestDTO.identityNo());


        return customer;
    }
    public Customer toCustomer(Long id,UpdateCustomerRequestDTO updateCustomerRequestDTO){
        Customer customer = customerRepository.findById(id).orElseThrow(
                ()-> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found")
        );
        String encodedPassword = bCryptPasswordEncoder.encode(updateCustomerRequestDTO.password());
        User user = customer.getUser();
        user.setName(updateCustomerRequestDTO.name());
        user.setPassword(encodedPassword);
        user.setEmail(updateCustomerRequestDTO.email());
        user.setPhoneNumber(updateCustomerRequestDTO.phoneNumber());
        user.setUpdatedAt(new Date());
        customer.setIdentityNo(updateCustomerRequestDTO.identityNo());
        customer.setUser(user);
        return customer;
    }

}
