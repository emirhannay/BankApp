package com.example.bankapp.dto.response;

import java.math.BigDecimal;
import java.util.Date;

public record GetSavingsAccountMaturityResponseDTO(BigDecimal amount, int month, BigDecimal moneyWitInterest,
                                                   Date endDate,
                                                   Date startDate) {
}
