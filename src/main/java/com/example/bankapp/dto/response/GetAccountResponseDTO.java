package com.example.bankapp.dto.response;

import com.example.bankapp.entity.enums.AccountType;
import com.example.bankapp.entity.enums.Currency;

import java.math.BigDecimal;

public record GetAccountResponseDTO(String iban, BigDecimal balance, Currency currency, AccountType accountType) {
}
