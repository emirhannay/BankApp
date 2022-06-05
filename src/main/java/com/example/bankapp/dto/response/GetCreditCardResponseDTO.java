package com.example.bankapp.dto.response;

import com.example.bankapp.entity.enums.CreditCardStatus;

import java.math.BigDecimal;
import java.util.Date;

public record GetCreditCardResponseDTO(BigDecimal cardLimit,
                                       BigDecimal lastOutstandingBalance,
                                       BigDecimal currentDebt,
                                       BigDecimal availableLimit,
                                       Date cutoffDate,
                                       Date nextCutoffDate,
                                       Date paymentDueDate,
                                       Date nextPaymentDueDate,
                                       CreditCardStatus creditCardStatus) {
}
