package com.example.bankapp.controller;

import com.example.bankapp.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users/customers")
public class CreditCardController {

    private final CreditCardService creditCardService;

    //Creates a credit card
    @PostMapping("/{id}/cards/credit")
    ResponseEntity<?> create(@PathVariable(value = "id") Long customerId) {
        creditCardService.create(customerId);
        return ResponseEntity.ok().body("Credit card is created successfully");
    }

    //Returns credit card information by customer id.
    @GetMapping("/{id}/cards/credit")
    ResponseEntity<?> getCreditCardByCustomerId(@PathVariable(value = "id") Long customerId) {

        return ResponseEntity.ok().body(creditCardService.getCreditCardByCustomerId(customerId));
    }

    @GetMapping("/cards/credit")
    ResponseEntity<?> getCreditCardActivities(@RequestParam(name = "cardNo",required = true) String cardNo) {

        return ResponseEntity.ok().body(creditCardService.getCreditCardActivitiesByCardNo(cardNo));
    }
}
