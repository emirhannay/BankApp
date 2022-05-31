package com.example.bankapp.dto.request;

import java.math.BigDecimal;

public record DepositMoneyToSavingAccountRequest(BigDecimal amount, Long savingsAccountId, int month) {
}
