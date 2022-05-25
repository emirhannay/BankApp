package com.example.bankapp.service;

import com.example.bankapp.dto.request.CreateSavingsAccountRequestDTO;

public interface SavingsAccountService {
    void create(CreateSavingsAccountRequestDTO createSavingsAccountRequestDTO);
    void depositMoneyToSavingsAccount();
}
