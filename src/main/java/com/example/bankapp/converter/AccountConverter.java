package com.example.bankapp.converter;

import com.example.bankapp.dto.response.GetAccountResponseDTO;
import com.example.bankapp.dto.response.GetCorporateAccountResponseDTO;
import com.example.bankapp.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter {

    public GetAccountResponseDTO toGetAccountResponseDto(Account account){
        /*return new GetAccountResponseDTO(account.getIban(),account.getBalance(),account.getCurrency(),account.getAccountType());*/
        return null;
    }
    public GetCorporateAccountResponseDTO toGetCorporateAccountResponseDTO(Account account){
        return new GetCorporateAccountResponseDTO(account.getCustomer().getUser().getName(),
                                                  account.getIban());
    }

}
