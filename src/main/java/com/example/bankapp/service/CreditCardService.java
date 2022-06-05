package com.example.bankapp.service;

import com.example.bankapp.dto.response.GetCreditCardResponseDTO;

public interface CreditCardService {
    void create(Long customerId);
    GetCreditCardResponseDTO getCreditCardByCustomerId(Long customerId);
}
