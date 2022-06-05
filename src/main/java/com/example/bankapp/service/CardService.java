package com.example.bankapp.service;

import com.example.bankapp.dto.response.GetAccountResponseDTO;
import com.example.bankapp.dto.response.GetCardResponseDTO;

import java.util.List;

public interface CardService {

    List<GetCardResponseDTO> getAllCards();
    List<GetCardResponseDTO> getCardsByCustomerId(Long customerId);
}
