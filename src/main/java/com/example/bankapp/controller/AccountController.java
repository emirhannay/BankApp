package com.example.bankapp.controller;

import com.example.bankapp.model.SendMoneyRequest;
import com.example.bankapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class AccountController {


    private final AccountService accountService;

    @PostMapping("/customers/accounts/transfer")
    public ResponseEntity<?> sendMoney(@RequestBody SendMoneyRequest sendMoneyRequest){
        accountService.sendMoney(sendMoneyRequest);

        return ResponseEntity.ok().body("Money transfer successfully");

    }
    @GetMapping("/customers/{}/accounts")
    public ResponseEntity<?> getAccountsByCustomerId(@PathVariable Long customerId){

        return ResponseEntity.ok().body(accountService.getAccountsByCustomerId(customerId));

    }

}
