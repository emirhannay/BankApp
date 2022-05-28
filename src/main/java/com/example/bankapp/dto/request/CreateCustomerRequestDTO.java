package com.example.bankapp.dto.request;

public record CreateCustomerRequestDTO(String email,String password,String name,String identityNo,String phoneNumber ) {
}
