package com.example.bankapp.controller;

import com.example.bankapp.dto.request.PayRequestDTO;
import com.example.bankapp.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("api/pay")
@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    //Public payment api where you can make payments to corporate accounts.
    //All you have to do is give your card information and the iban information you will make the payment.
    @PostMapping
    ResponseEntity<?> pay(@RequestBody PayRequestDTO payRequestDTO) {
        paymentService.pay(payRequestDTO);
        return ResponseEntity.ok().body("Payment was successful.");
    }
}
