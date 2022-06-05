package com.example.bankapp.service;

import com.example.bankapp.dto.request.PayRequestDTO;
import com.example.bankapp.dto.response.GetCreditCardActivitiesResponseDTO;

import java.util.List;

public interface PaymentService {
    void pay(PayRequestDTO payRequestDTO);

}
