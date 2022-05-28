package com.example.bankapp.model;

import com.example.bankapp.entity.enums.Currency;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
//Model created to get required data from exchange rate api
public class ExchangeRateApiData {
    @JsonProperty("base")
    private final Currency base;
    @JsonProperty("rates")
    private final Rates rates;


    public ExchangeRateApiData(@JsonProperty(value = "base", required = true) Currency base,
                               @JsonProperty(value = "rates", required = true)Rates rates) {
        this.base = base;
        this.rates = rates;
    }

    @Getter
    public static class Rates{
        private final BigDecimal eur;
        private final BigDecimal tl;
        private final BigDecimal usd;

        public Rates(@JsonProperty(value = "EUR", required = true)BigDecimal eur,
                     @JsonProperty(value = "TRY", required = true)BigDecimal tl,
                     @JsonProperty(value = "USD", required = true)BigDecimal usd) {
            this.eur = eur;
            this.tl = tl;
            this.usd = usd;
        }
    }
}
