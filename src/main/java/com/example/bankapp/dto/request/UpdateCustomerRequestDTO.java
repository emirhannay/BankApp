package com.example.bankapp.dto.request;

public record UpdateCustomerRequestDTO(String name,
                                       String identityNo,
                                       String password,
                                       String email,
                                       String phoneNumber) {
}
