package com.example.bankapp.controller;

import com.example.bankapp.dto.request.CreateDrawingAccountRequestDTO;
import com.example.bankapp.dto.request.CreateSavingsAccountRequestDTO;
import com.example.bankapp.dto.request.DepositMoneyToSavingAccountRequest;
import com.example.bankapp.repository.SavingsAccountMaturityRepository;
import com.example.bankapp.service.SavingsAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/accounts/savings")
public class SavingsAccountController {

    private final SavingsAccountService savingsAccountService;
    private final SavingsAccountMaturityRepository savingsAccountMaturityRepository;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateSavingsAccountRequestDTO createSavingsAccountRequestDTO){
        savingsAccountService.create(createSavingsAccountRequestDTO);

        return ResponseEntity.ok().body("Savings account is created successfully");

    }
    @PostMapping("/deposit")
    public ResponseEntity<?> depositMoney(@RequestBody DepositMoneyToSavingAccountRequest depositMoneyToSavingAccountRequest){
        savingsAccountService.depositMoneyToSavingsAccount(depositMoneyToSavingAccountRequest);

        return ResponseEntity.ok().body("The money has been deposited successfully.");

    }


}
