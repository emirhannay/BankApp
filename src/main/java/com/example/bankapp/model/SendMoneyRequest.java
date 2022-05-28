package com.example.bankapp.model;

import java.math.BigDecimal;

public record SendMoneyRequest(String senderIban , String receiverIban , BigDecimal amount){
}
