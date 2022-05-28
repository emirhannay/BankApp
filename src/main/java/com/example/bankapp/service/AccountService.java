package com.example.bankapp.service;

import com.example.bankapp.exception.BaseException;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.model.SendMoneyRequest;

public interface AccountService {
    void sendMoney(SendMoneyRequest sendMoneyRequestDTO) throws BaseException;
}
