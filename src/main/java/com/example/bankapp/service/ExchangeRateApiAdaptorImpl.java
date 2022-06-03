package com.example.bankapp.service;

import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.enums.Currency;
import com.example.bankapp.model.ExchangeRateApiData;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
//External API system integration
public class ExchangeRateApiAdaptorImpl implements ExchangeRateApiAdaptor{

    private final RestTemplate restTemplate;

    public ExchangeRateApiAdaptorImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ExchangeRateApiData getExchangeRates(Currency currency){
        HttpHeaders headers= new HttpHeaders();
        headers.set("apikey","K88D3WKrakMkOQZ6p6KnW8ba0XC17LLK");
        HttpEntity<String> entity = new HttpEntity<>("paramters",headers);
        ResponseEntity<ExchangeRateApiData> responseEntity = restTemplate.
                exchange("https://api.apilayer.com" +
                                "/exchangerates_data" +
                                "/latest?symbols=USD,EUR,TRY" +
                                "&base=" + currency,
                         HttpMethod.GET,entity,
                        ExchangeRateApiData.class);

        return responseEntity.getBody();
    }

    public BigDecimal getExchangeRateForTransfer(Account senderAccount, Account receiverAccount){
        ExchangeRateApiData exchangeRateApiData = getExchangeRates(senderAccount.getCurrency());
        if(receiverAccount.getCurrency() == Currency.TRY){
            return exchangeRateApiData.getRates().getTl();
        }
        if(receiverAccount.getCurrency() == Currency.EUR){
            return exchangeRateApiData.getRates().getEur();
        }
        if(receiverAccount.getCurrency() == Currency.USD){
            return exchangeRateApiData.getRates().getUsd();
        }
        return BigDecimal.ZERO;
    }
    public BigDecimal getTlExchangeRateForTransfer( Account receiverAccount){
        ExchangeRateApiData exchangeRateApiData = getExchangeRates(Currency.TRY);
        if(receiverAccount.getCurrency() == Currency.TRY){
            return exchangeRateApiData.getRates().getTl();
        }
        if(receiverAccount.getCurrency() == Currency.EUR){
            return exchangeRateApiData.getRates().getEur();
        }
        if(receiverAccount.getCurrency() == Currency.USD){
            return exchangeRateApiData.getRates().getUsd();
        }
        return BigDecimal.ZERO;
    }

}
