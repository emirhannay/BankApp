package com.example.bankapp.service;

import com.example.bankapp.converter.SavingsAccountConverter;
import com.example.bankapp.dto.request.CreateSavingsAccountRequestDTO;
import com.example.bankapp.entity.SavingsAccount;
import com.example.bankapp.repository.SavingsAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SavingsAccountServiceImpl implements SavingsAccountService{

    private final SavingsAccountConverter savingsAccountConverter;
    private final SavingsAccountRepository savingsAccountRepository;
    @Override
    public void create(CreateSavingsAccountRequestDTO createSavingsAccountRequestDTO) {
        SavingsAccount savingsAccount = savingsAccountConverter.toSavingsAccount(createSavingsAccountRequestDTO);
        savingsAccountRepository.save(savingsAccount);
        log.info("Savings Account was successfully created -> {}" ,savingsAccount.getId());
    }

    @Override
    public void depositMoneyToSavingsAccount() {

    }
}
