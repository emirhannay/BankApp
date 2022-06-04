package com.example.bankapp.converter;

import com.example.bankapp.dto.response.GetAccountResponseDTO;
import com.example.bankapp.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {

    public GetAccountResponseDTO toGetAccountResponseDto(Account account){
        return new GetAccountResponseDTO(account.getIban(),account.getBalance(),account.getCurrency(),account.getAccountType());
    }

}
