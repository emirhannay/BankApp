package com.example.bankapp.dto.response;

import com.example.bankapp.entity.enums.Currency;

import java.math.BigDecimal;

public record GetAccountActivitiesResponseDTO(String iban, BigDecimal amount, Currency currency) {
}
