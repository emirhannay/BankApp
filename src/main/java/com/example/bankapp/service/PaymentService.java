package com.example.bankapp.service;

import com.example.bankapp.dto.request.PayRequestDTO;

public interface PaymentService {
    public void pay(PayRequestDTO payRequestDTO);
}
