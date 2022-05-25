package com.example.bankapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
//Model created to get required data from exchange rate api
public class ExchangeRateApiData {
    @JsonProperty("base")
    private final String base;
    @JsonProperty("rates")
    private final Rates rates;


    public ExchangeRateApiData(@JsonProperty(value = "base", required = true) String base,
                               @JsonProperty(value = "rates", required = true)Rates rates) {
        this.base = base;
        this.rates = rates;
    }

    @Getter
    public static class Rates{
        private final String eur;
        private final String tl;
        private final String usd;

        public Rates(@JsonProperty(value = "EUR", required = true)String eur,
                     @JsonProperty(value = "TRY", required = true)String tl,
                     @JsonProperty(value = "USD", required = true)String usd) {
            this.eur = eur;
            this.tl = tl;
            this.usd = usd;
        }
    }
}
