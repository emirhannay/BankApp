package com.example.bankapp.dto.request;

import com.example.bankapp.entity.enums.Currency;

public record CreateSavingsAccountRequestDTO(Long customerId, Currency currency){
}
