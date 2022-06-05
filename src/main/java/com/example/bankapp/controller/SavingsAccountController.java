package com.example.bankapp.controller;

import com.example.bankapp.dto.request.CreateDrawingAccountRequestDTO;
import com.example.bankapp.dto.request.CreateSavingsAccountRequestDTO;
import com.example.bankapp.dto.request.DepositMoneyToSavingAccountRequest;
import com.example.bankapp.repository.SavingsAccountMaturityRepository;
import com.example.bankapp.service.SavingsAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users/customers/accounts/savings")
public class SavingsAccountController {

    private final SavingsAccountService savingsAccountService;
    private final SavingsAccountMaturityRepository savingsAccountMaturityRepository;

    //Creates a savings account
    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateSavingsAccountRequestDTO createSavingsAccountRequestDTO){
        savingsAccountService.create(createSavingsAccountRequestDTO);

        return ResponseEntity.ok().body("Savings account is created successfully");

    }
    //Deposits money into savings account
    @PostMapping("/deposit")
    public ResponseEntity<?> depositMoney(@RequestBody DepositMoneyToSavingAccountRequest depositMoneyToSavingAccountRequest){
        savingsAccountService.depositMoneyToSavingsAccount(depositMoneyToSavingAccountRequest);
        return ResponseEntity.ok().body("The money has been deposited successfully.");
    }

    @GetMapping()
    public ResponseEntity<?> getSavingAccountMaturitiesByIban(@RequestParam(name = "iban",required = false) String iban){

        return ResponseEntity.ok().body(savingsAccountService.getSavingAccountMaturitiesByIban(iban));
    }


}
