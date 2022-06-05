package com.example.bankapp.dto.response;

import com.example.bankapp.entity.enums.Currency;

import java.math.BigDecimal;
import java.util.Date;

public record GetCreditCardActivitiesResponseDTO(String iban,
                                                 BigDecimal amount,
                                                 Currency currency,
                                                 Date paymentDate,
                                                 String shop) {
}
