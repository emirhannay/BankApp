package com.example.bankapp.service;

import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.enums.Currency;
import com.example.bankapp.model.ExchangeRateApiData;

import java.math.BigDecimal;

public interface ExchangeRateApiAdaptor {
    ExchangeRateApiData getExchangeRates(Currency currency);
    BigDecimal getExchangeRateForTransfer(Account senderAccount, Account receiverAccount);
    BigDecimal getTlExchangeRateForTransfer( Account receiverAccount);
}
