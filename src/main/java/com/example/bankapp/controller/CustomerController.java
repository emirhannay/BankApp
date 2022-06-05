package com.example.bankapp.controller;

import com.example.bankapp.converter.CustomerConverter;
import com.example.bankapp.dto.request.CreateCustomerRequestDTO;
import com.example.bankapp.dto.request.UpdateCustomerRequestDTO;
import com.example.bankapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/users/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerConverter customerConverter;

    @GetMapping("/{id}")
    ResponseEntity<?> getCustomer(@PathVariable Long id) {
        return ResponseEntity.ok().body(customerConverter.
                getCustomerResponseDTO(customerService.getCustomerWithId(id))
        );
    }
    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<?> getCustomers() {
        return ResponseEntity.ok().body(customerService.getCustomers());
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
