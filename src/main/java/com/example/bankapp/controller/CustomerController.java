package com.example.bankapp.controller;

import com.example.bankapp.dto.request.CreateCustomerRequestDTO;
import com.example.bankapp.dto.request.UpdateCustomerRequestDTO;
import com.example.bankapp.entity.Customer;
import com.example.bankapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/users/customers")
@RequiredArgsConstructor
@Secured({})
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    ResponseEntity<?> create(@RequestBody CreateCustomerRequestDTO createCustomerRequestDTO) {
        customerService.save(createCustomerRequestDTO);
        return ResponseEntity.ok().body("Customer is created successfully");
    }

    @PutMapping("/{id}")
    ResponseEntity<?> update(@PathVariable Long id,@RequestBody UpdateCustomerRequestDTO updateCustomerRequestDTO) {
        customerService.update(id,updateCustomerRequestDTO);
        return ResponseEntity.ok().body("Customer is updated successfully");
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id,@RequestParam(name = "hardDelete",required = false) boolean hardDelete) {
        customerService.delete(id,hardDelete);
        return ResponseEntity.ok().body("Customer is deleted successfully");
    }
    
}
