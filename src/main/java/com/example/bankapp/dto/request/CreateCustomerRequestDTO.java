package com.example.bankapp.dto.request;

import java.math.BigDecimal;

public record CreateCustomerRequestDTO(BigDecimal monthlyEarning, String email, String password, String name, String identityNo, String phoneNumber ) {
}
