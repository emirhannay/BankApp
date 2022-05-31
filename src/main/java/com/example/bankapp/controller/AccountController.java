package com.example.bankapp.controller;

import com.example.bankapp.model.SendMoneyRequest;
import com.example.bankapp.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/accounts")
public class AccountController {


    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<?> sendMoney(@RequestBody SendMoneyRequest sendMoneyRequest){
        accountService.sendMoney(sendMoneyRequest);

        return ResponseEntity.ok().body("Money transfer successfully");

    }

}
