package com.example.bankapp.converter;

import com.example.bankapp.dto.response.GetAccountsResponseDTO;
import com.example.bankapp.dto.response.GetCorporateAccountResponseDTO;
import com.example.bankapp.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {

    public GetAccountsResponseDTO toGetAccountsResponseDto(Account account){
        return new GetAccountsResponseDTO(account.getIban(),account.getBalance(),account.getCurrency(),account.getAccountType());

    }
    public GetCorporateAccountResponseDTO toGetCorporateAccountResponseDTO(Account account){
        return new GetCorporateAccountResponseDTO(account.getCustomer().getUser().getName(),
                                                  account.getIban());
    }

}
