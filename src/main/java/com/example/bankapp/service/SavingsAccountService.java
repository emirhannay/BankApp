package com.example.bankapp.service;

import com.example.bankapp.dto.request.CreateSavingsAccountRequestDTO;
import com.example.bankapp.dto.request.DepositMoneyToSavingAccountRequest;
import com.example.bankapp.dto.response.GetSavingsAccountMaturityResponseDTO;

import java.util.List;

public interface SavingsAccountService {
    void create(CreateSavingsAccountRequestDTO createSavingsAccountRequestDTO);
    void depositMoneyToSavingsAccount(DepositMoneyToSavingAccountRequest depositMoneyToSavingAccountRequest);
    List<GetSavingsAccountMaturityResponseDTO> getSavingsAccountMaturitiesByIban(String iban);
}
