package com.example.bankapp.service;

import com.example.bankapp.converter.SavingsAccountConverter;
import com.example.bankapp.dto.request.CreateSavingsAccountRequestDTO;
import com.example.bankapp.entity.Customer;
import com.example.bankapp.entity.SavingsAccount;
import com.example.bankapp.entity.SavingsAccountMaturity;
import com.example.bankapp.entity.User;
import com.example.bankapp.entity.enums.SavingsAccountMaturityStatus;
import com.example.bankapp.entity.enums.UserType;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.dto.request.DepositMoneyToSavingAccountRequest;
import com.example.bankapp.helper.UserHelper;
import com.example.bankapp.repository.SavingsAccountMaturityRepository;
import com.example.bankapp.repository.SavingsAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class SavingsAccountServiceImpl implements SavingsAccountService{

    private final SavingsAccountConverter savingsAccountConverter;
    private final SavingsAccountRepository savingsAccountRepository;
    private final UserHelper userHelper;
    private final SavingsAccountMaturityRepository savingsAccountMaturityRepository;
    private final BigDecimal bankRate = BigDecimal.valueOf(5);
    @Override
    public void create(CreateSavingsAccountRequestDTO createSavingsAccountRequestDTO) {
        SavingsAccount savingsAccount = savingsAccountConverter.toSavingsAccount(createSavingsAccountRequestDTO);
        savingsAccountRepository.save(savingsAccount);
        log.info("Savings Account was successfully created -> {}" ,savingsAccount.getId());
    }

    @Override
    public void depositMoneyToSavingsAccount(DepositMoneyToSavingAccountRequest depositMoneyToSavingAccountRequest) {
        SavingsAccount savingsAccount = savingsAccountRepository.findById(depositMoneyToSavingAccountRequest.savingsAccountId())
                .orElseThrow(
                        ()-> new BusinessServiceOperationException.SavingsAccountNotFoundException("Savings account not found")
                );
        User user = userHelper.getLoggedInUser();
        User accountHolderUser = savingsAccount.getAccount().getCustomer().getUser();
        if( !(user == accountHolderUser) &&  !(userHelper.isLoggedInUserAdmin()) ){
            throw new BusinessServiceOperationException.DepositMoneyToSavingsAccountFailedException("Deposit money failed!");
        }
        else {
            BigDecimal currentBankRate = bankRate.multiply(BigDecimal.valueOf(depositMoneyToSavingAccountRequest.month()));
            BigDecimal moneyWithInterest = depositMoneyToSavingAccountRequest.amount().multiply(
                    BigDecimal.valueOf(100).add(currentBankRate).divide(BigDecimal.valueOf(100))
            );
            SavingsAccountMaturity savingsAccountMaturity = new SavingsAccountMaturity();
            savingsAccountMaturity.setSavingsAccount(savingsAccount);
            savingsAccountMaturity.setAmount(depositMoneyToSavingAccountRequest.amount());
            savingsAccountMaturity.setMonth(depositMoneyToSavingAccountRequest.month());
            savingsAccountMaturity.setStartDate(new Date());
            savingsAccountMaturity.setEndDate(maturityCalculator(depositMoneyToSavingAccountRequest.month()));
            savingsAccountMaturity.setMoneyWithInterest(moneyWithInterest);
            savingsAccountMaturity.setSavingsAccountMaturityStatus(SavingsAccountMaturityStatus.PENDING);
            savingsAccountMaturityRepository.save(savingsAccountMaturity);
        }

    }

    public Date maturityCalculator(int month){
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH,month);
        Date endDate = c.getTime();
        return endDate;
    }
}
