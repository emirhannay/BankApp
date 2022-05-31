package com.example.bankapp.controller;

import com.example.bankapp.dto.request.CreateDrawingAccountRequestDTO;
import com.example.bankapp.entity.enums.Currency;
import com.example.bankapp.service.DrawingAccountServiceImpl;
import com.example.bankapp.service.ExchangeRateApiAdaptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/accounts/drawings")
@Slf4j
public class DrawingAccountController {

    private final DrawingAccountServiceImpl drawingAccountService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateDrawingAccountRequestDTO createDrawingAccountRequestDTO){
        drawingAccountService.create(createDrawingAccountRequestDTO);

        return ResponseEntity.ok().body("Drawing account is created successfully");

    }

}
