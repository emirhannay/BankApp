package com.example.bankapp.service;

import com.example.bankapp.dto.request.CreateSavingsAccountRequestDTO;
import com.example.bankapp.dto.request.DepositMoneyToSavingAccountRequest;

public interface SavingsAccountService {
    void create(CreateSavingsAccountRequestDTO createSavingsAccountRequestDTO);
    void depositMoneyToSavingsAccount(DepositMoneyToSavingAccountRequest depositMoneyToSavingAccountRequest);
}
