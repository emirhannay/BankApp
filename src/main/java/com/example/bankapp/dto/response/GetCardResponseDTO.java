package com.example.bankapp.dto.response;

import com.example.bankapp.entity.enums.CardType;

import java.util.Date;

public record GetCardResponseDTO(String cardNo, Date expirationDate, String cvv, CardType cardType) {
}
