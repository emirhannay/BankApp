package com.example.bankapp.service.cronjob;

import com.example.bankapp.entity.SavingsAccount;
import com.example.bankapp.entity.SavingsAccountMaturity;
import com.example.bankapp.entity.enums.SavingsAccountMaturityStatus;
import com.example.bankapp.repository.SavingsAccountMaturityRepository;
import com.example.bankapp.repository.SavingsAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SavingsAccountCronJob {

    private final SavingsAccountMaturityRepository savingsAccountMaturityRepository;
    private final SavingsAccountRepository savingsAccountRepository;



    //Deposits money into account at 9am every morning.
    @Scheduled(cron = "0 0 9 * * *",zone = "Europe/Istanbul")
    public void depositMoneyToAccount(){
        List<SavingsAccountMaturity> savingsAccountMaturities = savingsAccountMaturityRepository.getAllSavingsAccountMaturitiesByEndDateAndStatus(new Date());
        for(int i = 0; i < savingsAccountMaturities.size(); i++){
            SavingsAccountMaturity savingsAccountMaturity = savingsAccountMaturities.get(i);
            SavingsAccount savingsAccount = savingsAccountRepository.getById(savingsAccountMaturity.getSavingsAccount().getId());
            savingsAccount.getAccount().setBalance(savingsAccount.getAccount().getBalance().add(savingsAccountMaturity.getMoneyWithInterest()));
            savingsAccountMaturity.setSavingsAccountMaturityStatus(SavingsAccountMaturityStatus.SENT);
            savingsAccountMaturityRepository.save(savingsAccountMaturity);

        }


    }
}
