package com.example.bankapp.controller;

import com.example.bankapp.dto.request.CreateCustomerRequestDTO;
import com.example.bankapp.repository.CreditCardRepository;
import com.example.bankapp.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users/customers/credit-cards")
public class CreditCardController {

    private final CreditCardService creditCardService;

    @PostMapping("/{id}")
    ResponseEntity<?> create(@PathVariable(value = "id") Long customerId) {
        creditCardService.create(customerId);
        return ResponseEntity.ok().body("Credit card is created successfully");
    }
}
