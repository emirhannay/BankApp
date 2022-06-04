package com.example.bankapp.service;

import com.example.bankapp.dto.response.GetAccountResponseDTO;
import com.example.bankapp.exception.BaseException;
import com.example.bankapp.model.SendMoneyRequest;

import java.util.List;

public interface AccountService {
    void sendMoney(SendMoneyRequest sendMoneyRequestDTO) throws BaseException;
    List<GetAccountResponseDTO> getAllAccounts();
    List<GetAccountResponseDTO> getAccountsByCustomerId(Long customerId);

}
