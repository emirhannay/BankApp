package com.example.bankapp.service;

import com.example.bankapp.converter.DrawingAccountConverter;
import com.example.bankapp.entity.DrawingAccount;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.repository.DrawingAccountRepository;
import com.example.bankapp.dto.request.CreateDrawingAccountRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
@Service
public class DrawingAccountServiceImpl implements DrawingAccountService{

    private final DrawingAccountRepository drawingAccountRepository;
    private final DrawingAccountConverter drawingAccountConverter;

    @Override
    public void create(CreateDrawingAccountRequestDTO createDrawingAccountRequestDTO) {
        DrawingAccount drawingAccount = drawingAccountConverter.toDrawingAccount(createDrawingAccountRequestDTO);
            drawingAccountRepository.save(drawingAccount);
            log.info("Drawing account was successfully created -> {}" ,drawingAccount.getId());

    }

    public DrawingAccount getDrawingAccountByCustomerId(Long customerId){
        DrawingAccount drawingAccount = drawingAccountRepository.findDrawingAccountByCustomerId(customerId);
        return drawingAccount;
    }
}
