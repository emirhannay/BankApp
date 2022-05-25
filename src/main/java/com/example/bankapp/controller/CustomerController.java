package com.example.bankapp.controller;

import com.example.bankapp.dto.request.CreateCustomerRequestDTO;
import com.example.bankapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    ResponseEntity<?> create(@RequestBody CreateCustomerRequestDTO createCustomerRequestDTO) {
        customerService.save(createCustomerRequestDTO);
        return ResponseEntity.ok().body("Customer is created successfully");
    }
    
}
