package com.example.bankapp.controller;

import com.example.bankapp.dto.request.CreateDrawingAccountRequestDTO;
import com.example.bankapp.entity.enums.Currency;
import com.example.bankapp.service.DrawingAccountServiceImpl;
import com.example.bankapp.service.ExchangeRateApiAdaptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users/customers")
@Slf4j
public class DrawingAccountController {

    private final DrawingAccountServiceImpl drawingAccountService;

    @PostMapping("/accounts/drawings")
    public ResponseEntity<?> create(@RequestBody CreateDrawingAccountRequestDTO createDrawingAccountRequestDTO){
        drawingAccountService.create(createDrawingAccountRequestDTO);
        return ResponseEntity.ok().body("Drawing account is created successfully");
    }

    @GetMapping("/{id}/accounts/drawings")
    public ResponseEntity<?> getDrawingAccounts(@PathVariable Long id){
        return ResponseEntity.ok().body(drawingAccountService.getDrawingAccountsByCustomerId(id));
    }

    @GetMapping("/accounts/drawings/{id}")
    public ResponseEntity<?> getDrawingAccount(@PathVariable Long id){
        return ResponseEntity.ok().body(drawingAccountService.getDrawingAccount(id));
    }

}
