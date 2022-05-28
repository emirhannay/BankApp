package com.example.bankapp.service;

import com.example.bankapp.entity.enums.Currency;
import com.example.bankapp.model.ExchangeRateApiData;

public interface ExchangeRateApiAdaptor {
    ExchangeRateApiData getExchangeRates(Currency currency);
}
