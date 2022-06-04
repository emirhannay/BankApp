package com.example.bankapp.service;

import com.example.bankapp.dto.response.GetAccountActivitiesResponseDTO;

import java.util.List;

public interface TransferService {

    List<GetAccountActivitiesResponseDTO> getAccountActivities(String iban);

}
