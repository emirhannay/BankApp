package com.example.bankapp.controller;

import com.example.bankapp.model.SendMoneyRequest;
import com.example.bankapp.service.AccountService;
import com.example.bankapp.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
public class AccountController {


    private final AccountService accountService;
    private final TransferService transferService;

    @PostMapping("/customers/accounts/transfer")
    public ResponseEntity<?> sendMoney(@RequestBody SendMoneyRequest sendMoneyRequest){
        accountService.sendMoney(sendMoneyRequest);

        return ResponseEntity.ok().body("Money transfer successfully");

    }

    @GetMapping("/customers/{customerId}/accounts")
    public ResponseEntity<?> getAccountsByCustomerId(@PathVariable Long customerId){

        return ResponseEntity.ok().body(accountService.getAccountsByCustomerId(customerId));

    }

    @GetMapping("/customers/accounts/transfers")
    public ResponseEntity<?> getAccountsByCustomerId(@RequestParam(name = "iban",required = false) String iban){

        return ResponseEntity.ok().body(transferService.getAccountActivities(iban));

    }

    @GetMapping("/customers/accounts")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> getAllAccounts(){
        return ResponseEntity.ok().body(accountService.getAllAccounts());
    }


}
