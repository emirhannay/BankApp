package com.example.bankapp.service;

import com.example.bankapp.dto.response.GetAccountResponseDTO;
import com.example.bankapp.dto.response.GetAccountsResponseDTO;
import com.example.bankapp.dto.response.GetCorporateAccountResponseDTO;
import com.example.bankapp.exception.BaseException;
import com.example.bankapp.model.SendMoneyRequest;

import java.util.List;

public interface AccountService {
    void sendMoney(SendMoneyRequest sendMoneyRequestDTO) throws BaseException;
    List<GetAccountsResponseDTO> getAllAccounts();
    List<GetAccountResponseDTO> getAccountsByCustomerId(Long customerId);
    List<GetCorporateAccountResponseDTO> getCorporateAccounts();

}
