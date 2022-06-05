package com.example.bankapp.controller;

import com.example.bankapp.dto.request.CreateDrawingAccountRequestDTO;
import com.example.bankapp.service.DrawingAccountServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users/customers")
@Slf4j
public class DrawingAccountController {

    private final DrawingAccountServiceImpl drawingAccountService;

    //Creates a savings account
    @PostMapping("/accounts/drawings")
    public ResponseEntity<?> create(@RequestBody CreateDrawingAccountRequestDTO createDrawingAccountRequestDTO){
        drawingAccountService.create(createDrawingAccountRequestDTO);
        return ResponseEntity.ok().body("Drawing account is created successfully");
    }

    //Get all drawing accounts of the customer by customer id.
    @GetMapping("/{id}/accounts/drawings")
    public ResponseEntity<?> getDrawingAccountsByCustomerId(@PathVariable Long id){
        return ResponseEntity.ok().body(drawingAccountService.getDrawingAccountsByCustomerId(id));
    }

    //Get a specific drawing account of the customer.
    @GetMapping("/accounts/drawings/{id}")
    public ResponseEntity<?> getDrawingAccount(@PathVariable Long id){
        return ResponseEntity.ok().body(drawingAccountService.getDrawingAccount(id));
    }



}
