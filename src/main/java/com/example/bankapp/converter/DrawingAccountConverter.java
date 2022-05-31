package com.example.bankapp.converter;

import com.example.bankapp.core.utils.CreatorService;
import com.example.bankapp.entity.*;
import com.example.bankapp.entity.enums.AccountType;
import com.example.bankapp.entity.enums.CardType;
import com.example.bankapp.helper.UserHelper;
import com.example.bankapp.security.UserDetail;
import com.example.bankapp.dto.request.CreateDrawingAccountRequestDTO;
import com.example.bankapp.exception.BusinessServiceOperationException;
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
    private final UserHelper userHelper;

    public DrawingAccount toDrawingAccount(CreateDrawingAccountRequestDTO createDrawingAccountRequestDTO){
        Customer customer = userHelper.getLoggedInCustomer();
        BankCard bankCard = new BankCard();
        Card card = new Card();
        card.setCustomer(customer);
        card.setCardNo(creatorService.createCardNo());
        card.setCvv(creatorService.createCvv());
        card.setExpirationDate(creatorService.createExpirationDate());
        card.setCardType(CardType.BANK);
        bankCard.setCard(card);
        bankCard.setBalance(BigDecimal.ZERO);
        DrawingAccount drawingAccount = new DrawingAccount();
        Account account = new Account();
        account.setCurrency(createDrawingAccountRequestDTO.currency());
        account.setCreatedAt(new Date());
        account.setIban(creatorService.createIban());
        account.setBalance(BigDecimal.valueOf(5000));
        account.setCustomer(customer);
        account.setAccountType(AccountType.DRAWING);
        drawingAccount.setBankCard(bankCard);
        drawingAccount.setAccount(account);

        return drawingAccount;
    }




}
