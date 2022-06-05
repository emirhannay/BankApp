package com.example.bankapp.converter;

import com.example.bankapp.dto.response.GetCardResponseDTO;
import com.example.bankapp.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class CardConverter {

    public GetCardResponseDTO toGetCardResponseDTO(Card card){
        return new GetCardResponseDTO(card.getCardNo(),card.getExpirationDate(),card.getCvv(),card.getCardType());
    }
}
