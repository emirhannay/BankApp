package com.example.bankapp.dto.response;

import com.example.bankapp.entity.enums.Currency;

import java.math.BigDecimal;

public record GetDrawingAccountResponseDTO(String iban,
                                           BigDecimal balance,
                                           Currency currency,
                                           Long id,
                                           Long accountId
    ) {
}
