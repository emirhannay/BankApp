package com.example.bankapp.service;

import com.example.bankapp.dto.request.CreateCustomerRequestDTO;
import com.example.bankapp.dto.request.UpdateCustomerRequestDTO;
import com.example.bankapp.entity.Customer;

public interface CustomerService {
    Customer getCustomer(String username);
    void save(CreateCustomerRequestDTO createCustomerRequestDTO);
    void update(Long id,UpdateCustomerRequestDTO updateCustomerRequestDTO);
    void delete(Long id,boolean hardDelete);

}
