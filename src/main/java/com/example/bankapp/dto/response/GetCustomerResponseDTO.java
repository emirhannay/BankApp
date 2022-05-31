package com.example.bankapp.dto.response;

import com.example.bankapp.entity.enums.UserType;

import java.math.BigDecimal;

public record GetCustomerResponseDTO(Long customerId, Long userId, String identityNo, BigDecimal monthlyEarning, String email, String phoneNumber, UserType userType) {
}
