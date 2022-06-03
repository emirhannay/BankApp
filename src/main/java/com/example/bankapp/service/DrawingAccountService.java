package com.example.bankapp.service;

import com.example.bankapp.dto.request.CreateDrawingAccountRequestDTO;
import com.example.bankapp.dto.response.GetDrawingAccountResponseDTO;
import com.example.bankapp.entity.DrawingAccount;
import com.example.bankapp.exception.BaseException;

import java.util.List;

public interface DrawingAccountService {
    void create(CreateDrawingAccountRequestDTO createDrawingAccountRequestDTO) throws BaseException;
    List<GetDrawingAccountResponseDTO> getDrawingAccountsByCustomerId(Long customerId) throws BaseException;
    GetDrawingAccountResponseDTO getDrawingAccount(Long id) throws  BaseException;

}
