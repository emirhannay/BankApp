package com.example.bankapp.service;

import com.example.bankapp.model.ExchangeRateApiData;

public interface ExchangeRateApiAdaptor {
    ExchangeRateApiData getExchangeRate(String currency);
}
