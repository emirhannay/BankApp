package com.example.bankapp.dto.request;

import java.math.BigDecimal;

public record PayRequestDTO(String senderCardNo,
                            String receiverIban,
                            BigDecimal amount,
                            String cvv
                            ){}
