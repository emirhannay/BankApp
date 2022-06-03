package com.example.bankapp.service;

import com.example.bankapp.dto.request.CreateCustomerRequestDTO;
import com.example.bankapp.dto.request.UpdateCustomerRequestDTO;
import com.example.bankapp.dto.response.GetCustomerResponseDTO;
import com.example.bankapp.entity.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerService {
    Customer getCustomerWithId(Long id);
    List<GetCustomerResponseDTO> getCustomers();
    void save(CreateCustomerRequestDTO createCustomerRequestDTO);
    void update(Long id,UpdateCustomerRequestDTO updateCustomerRequestDTO);
    void delete(Long id,boolean hardDelete);

}
