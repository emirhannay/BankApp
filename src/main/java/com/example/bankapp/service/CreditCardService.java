package com.example.bankapp.service;

import com.example.bankapp.dto.response.GetCreditCardActivitiesResponseDTO;
import com.example.bankapp.dto.response.GetCreditCardResponseDTO;

import java.util.List;

public interface CreditCardService {
    void create(Long customerId);
    GetCreditCardResponseDTO getCreditCardByCustomerId(Long customerId);
    List<GetCreditCardActivitiesResponseDTO> getCreditCardActivitiesByCardNo(String cardNo);

}
