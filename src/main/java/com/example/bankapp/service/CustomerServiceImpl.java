package com.example.bankapp.service;

import com.example.bankapp.converter.CustomerConverter;
import com.example.bankapp.dto.request.CreateCustomerRequestDTO;
import com.example.bankapp.entity.Customer;
import com.example.bankapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerConverter customerConverter;

    @Override
    public Customer getCustomer(String username) {
       /* Customer customer = customerRepository.findByEmail(username).orElseThrow(
                () -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found")
        );
        log.info("Getting customer was successfully -> {}",customer.getId()); */
        return null;
    }

    @Override
    public void save(CreateCustomerRequestDTO createCustomerRequestDTO) {
        Customer customer = customerConverter.ToCustomer(createCustomerRequestDTO);
        customerRepository.save(customer);
        log.info("Customer created successfully -> {}",customer.getId());

    }
}
