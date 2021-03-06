package com.example.bankapp.service;

import com.example.bankapp.converter.AccountConverter;
import com.example.bankapp.dto.response.GetAccountResponseDTO;
import com.example.bankapp.dto.response.GetAccountsResponseDTO;
import com.example.bankapp.dto.response.GetCorporateAccountResponseDTO;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Customer;
import com.example.bankapp.entity.Transfer;
import com.example.bankapp.entity.User;
import com.example.bankapp.entity.enums.AccountType;
import com.example.bankapp.exception.BaseException;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.helper.UserHelper;
import com.example.bankapp.model.SendMoneyRequest;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.CustomerRepository;
import com.example.bankapp.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final UserHelper userHelper;
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final ExchangeRateApiAdaptor exchangeRateApiAdaptor;
    private final TransferRepository transferRepository;
    private final AccountConverter accountConverter;

    @Override
    @Transactional(rollbackOn = {BaseException.class, SQLException.class})
    public void sendMoney(SendMoneyRequest sendMoneyRequest) {
        Customer loggedInCustomer = userHelper.getLoggedInCustomer();
        Account senderAccount = accountRepository.findByIban(sendMoneyRequest.senderIban())
                .orElseThrow(
                        ()-> new BusinessServiceOperationException.AccountNotFoundException("Sender account not found")
                );
        Account receiverAccount = accountRepository.findByIban(sendMoneyRequest.receiverIban())
                .orElseThrow(
                        () -> new BusinessServiceOperationException.AccountNotFoundException("Receiver account not found")
                );
        BigDecimal moneyToBeSent = sendMoneyRequest.amount();
        if( receiverAccount.getAccountType() == AccountType.SAVINGS ){
            throw new BusinessServiceOperationException
                    .MoneyTransferFailedException("You cannot send money to savings account.But you can deposit money.");
        }
        if( receiverAccount.getAccountType() == AccountType.CORPORATE){
            throw new BusinessServiceOperationException
                    .MoneyTransferFailedException("You cannot send money to corporate account.");
        }
        if( !(senderAccount.getCustomer() == loggedInCustomer)) {
            throw new BusinessServiceOperationException
                    .MoneyTransferFailedException("You cannot send money from an account that does not belong to you.");
        }
        if(senderAccount == receiverAccount){
            throw new BusinessServiceOperationException
                    .MoneyTransferFailedException("Sender account and receiver account cannot be the same");
        }
        if( !(doesAccountHasEnoughMoneyForTransfer(senderAccount,sendMoneyRequest)) ){
            throw new BusinessServiceOperationException
                    .MoneyTransferFailedException("There is not enough money in your account.");
        }
        if( areAccountsSameCurrencyType(senderAccount,receiverAccount) ){
            senderAccount.setBalance(senderAccount.getBalance().subtract(sendMoneyRequest.amount()));
            receiverAccount.setBalance(receiverAccount.getBalance().add(sendMoneyRequest.amount()));
        }
        else {
            senderAccount.setBalance(senderAccount.getBalance().subtract(moneyToBeSent));
            moneyToBeSent = moneyToBeSent.multiply(exchangeRateApiAdaptor.getExchangeRateForTransfer(senderAccount,receiverAccount));
            receiverAccount.setBalance(receiverAccount.getBalance().add(moneyToBeSent));
        }
        accountRepository.save(senderAccount);
        accountRepository.save(receiverAccount);
        Transfer transfer = new Transfer();
        transfer.setTransferDate(new Date());
        transfer.setSenderIban(senderAccount.getIban());
        transfer.setReceiverIban(receiverAccount.getIban());
        transfer.setAmount(moneyToBeSent);
        transfer.setCurrency(receiverAccount.getCurrency());
        transfer.setDescription(sendMoneyRequest.description());
        transferRepository.save(transfer);


    }

    @Override
    public List<GetAccountsResponseDTO> getAllAccounts() {
        List<GetAccountsResponseDTO> accounts = accountRepository.findAll().stream().map(accountConverter::toGetAccountsResponseDto).toList();
        return accounts;
    }

    @Override
    public List<GetAccountResponseDTO> getAccountsByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found with tis id")
        );
        User loggedInUser = userHelper.getLoggedInUser();
        if( !(customer.getUser() == loggedInUser) &  !(userHelper.isLoggedInUserAdmin()) ){
            throw new BusinessServiceOperationException.GetCustomerFailedException("Get accounts operation failed ");
        }
        List<Account> accounts = accountRepository.getAllAccountsByCustomerId(customerId);
        List<GetAccountResponseDTO> accountResponseDTOS = new ArrayList<>();
        for(int i = 0; i < accounts.size(); i++){
            Account account = accounts.get(i);
            if(account.getAccountType() == AccountType.SAVINGS){
                GetAccountResponseDTO tempAccountResponseDTO = new GetAccountResponseDTO(account.getIban(),
                        account.getBalance(),
                        account.getCurrency(),
                        account.getAccountType(),
                        account.getSavingsAccount().getId(),
                        null);
                accountResponseDTOS.add(tempAccountResponseDTO);
            }
            else if( (account.getAccountType() == AccountType.DRAWING)  || (account.getAccountType() == AccountType.CORPORATE)){
                GetAccountResponseDTO tempAccountResponseDTO = new GetAccountResponseDTO(account.getIban(),
                        account.getBalance(),
                        account.getCurrency(),
                        account.getAccountType(),
                        null,
                        account.getDrawingAccount().getId());
                accountResponseDTOS.add(tempAccountResponseDTO);
            }
        }

        return accountResponseDTOS;
    }

    @Override
    public List<GetCorporateAccountResponseDTO> getCorporateAccounts() {
        List<GetCorporateAccountResponseDTO> accounts = accountRepository.getCorporateAccounts().stream().map(accountConverter::toGetCorporateAccountResponseDTO).toList();
        return accounts;
    }

    public boolean doesAccountHasEnoughMoneyForTransfer(Account senderAccount,SendMoneyRequest sendMoneyRequest){
        BigDecimal senderAccountBalance = senderAccount.getBalance();
        BigDecimal moneyToBeSent = sendMoneyRequest.amount();
        //compareTo returns -1 if balance is not enough
        if(senderAccountBalance.compareTo(moneyToBeSent) == -1){
            return false;
        }
        else {
            return true;
        }
    }
    public boolean areAccountsSameCurrencyType(Account senderAccount,Account receiverAccount){
        if(senderAccount.getCurrency() == receiverAccount.getCurrency()){
            return true;
        }
        else{
            return false;
        }
    }


}
