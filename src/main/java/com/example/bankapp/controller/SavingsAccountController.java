package com.example.bankapp.controller;

import com.example.bankapp.dto.request.CreateDrawingAccountRequestDTO;
import com.example.bankapp.dto.request.CreateSavingsAccountRequestDTO;
import com.example.bankapp.service.SavingsAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/savings-accounts")
public class SavingsAccountController {

    private final SavingsAccountService savingsAccountService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateSavingsAccountRequestDTO createSavingsAccountRequestDTO){
        savingsAccountService.create(createSavingsAccountRequestDTO);

        return ResponseEntity.ok().body("Drawing account is created successfully");

    }

}
