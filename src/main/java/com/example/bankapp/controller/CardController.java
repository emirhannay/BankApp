package com.example.bankapp.controller;

import com.example.bankapp.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users/customers")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    //Get all cards information.
    @GetMapping("/cards")
    @PreAuthorize("hasAuthority('ADMIN')")
    ResponseEntity<?> getCards(@PathVariable(value = "id") Long customerId) {
        return ResponseEntity.ok().body(cardService.getAllCards());
    }

    @GetMapping("/{}/cards")
    ResponseEntity<?> getCardsByCustomerId(@PathVariable(value = "id") Long customerId) {
        return ResponseEntity.ok().body(cardService.getCardsByCustomerId(customerId));
    }



}
