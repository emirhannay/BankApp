package com.example.bankapp.service;

import com.example.bankapp.converter.CardConverter;
import com.example.bankapp.dto.response.GetAccountResponseDTO;
import com.example.bankapp.dto.response.GetCardResponseDTO;
import com.example.bankapp.helper.UserHelper;
import com.example.bankapp.repository.CardRepository;
import com.example.bankapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final UserHelper userHelper;
    private final CustomerRepository customerRepository;
    private final CardRepository cardRepository;
    private final CardConverter cardConverter;

    @Override
    public List<GetCardResponseDTO> getAllCards() {


        return cardRepository.findAll().stream().map(cardConverter::toGetCardResponseDTO).toList();
    }

    @Override
    public List<GetCardResponseDTO> getCardsByCustomerId(Long customerId) {
        return null;
    }
}
