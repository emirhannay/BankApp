package com.example.bankapp.service;

import com.example.bankapp.dto.request.CreateDrawingAccountRequestDTO;
import com.example.bankapp.exception.BaseException;

public interface DrawingAccountService {
    void create(CreateDrawingAccountRequestDTO createDrawingAccountRequestDTO) throws BaseException;

}
