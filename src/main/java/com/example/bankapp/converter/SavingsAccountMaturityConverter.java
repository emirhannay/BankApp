package com.example.bankapp.converter;

import com.example.bankapp.dto.response.GetSavingsAccountMaturityResponseDTO;
import com.example.bankapp.entity.SavingsAccountMaturity;
import org.springframework.stereotype.Component;

@Component
public class SavingsAccountMaturityConverter {

    public GetSavingsAccountMaturityResponseDTO toGetSavingsAccountMaturityResponseDTO(SavingsAccountMaturity savingsAccountMaturity)
    {
        return new GetSavingsAccountMaturityResponseDTO(savingsAccountMaturity.getAmount(),
                savingsAccountMaturity.getMonth(),
                savingsAccountMaturity.getMoneyWithInterest(),
                savingsAccountMaturity.getEndDate(),
                savingsAccountMaturity.getStartDate());
    }
}
