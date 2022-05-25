package com.example.bankapp.converter;

import com.example.bankapp.core.utils.CreatorService;
import com.example.bankapp.entity.BankCard;
import com.example.bankapp.entity.DrawingAccount;
import com.example.bankapp.security.UserDetail;
import com.example.bankapp.dto.request.CreateDrawingAccountRequestDTO;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.entity.Customer;
import com.example.bankapp.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class DrawingAccountConverter {

    private final CustomerRepository customerRepository;
    private final CreatorService creatorService;

    public DrawingAccount toDrawingAccount(CreateDrawingAccountRequestDTO createDrawingAccountRequestDTO){
        UserDetail userDetail = (UserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = customerRepository.findById(userDetail.getId()).orElseThrow(
                ()-> new BusinessServiceOperationException.CustomerNotFoundException("Customer Not Found")
        );
        BankCard bankCard = new BankCard();
        bankCard.setCustomer(customer);
        bankCard.setCardNo(creatorService.createCardNo());
        bankCard.setCvv(creatorService.createCvv());
        bankCard.setExpirationDate(creatorService.createExpirationDate());

        DrawingAccount drawingAccount = new DrawingAccount();
        drawingAccount.setCurrency(createDrawingAccountRequestDTO.currency());
        drawingAccount.setCustomer(customer);
        drawingAccount.setCreatedAt(new Date());
        drawingAccount.setIban(creatorService.createIban());
        drawingAccount.setBalance(BigDecimal.ZERO);
        drawingAccount.setBankCard(bankCard);
        drawingAccount.setDeleted(false);
        drawingAccount.setCreatedAt(new Date());
        return drawingAccount;
    }




}
