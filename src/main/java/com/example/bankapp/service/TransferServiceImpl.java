package com.example.bankapp.service;

import com.example.bankapp.dto.response.GetAccountActivitiesResponseDTO;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Transfer;
import com.example.bankapp.entity.User;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.helper.UserHelper;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.TransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class    TransferServiceImpl implements TransferService{


    private final TransferRepository transferRepository;
    private final AccountRepository accountRepository;
    private final UserHelper userHelper;



    @Override
    public List<GetAccountActivitiesResponseDTO> getAccountActivities(String iban) {
        User loggedInUser = userHelper.getLoggedInUser();
        Account account = accountRepository.findByIban(iban).orElseThrow(
                () -> new BusinessServiceOperationException.AccountNotFoundException("Account not found")
        );
        if( !(account.getCustomer().getUser() == loggedInUser) &  !(userHelper.isLoggedInUserAdmin()) ){
            throw new BusinessServiceOperationException.GetCustomerFailedException("Get account activities operation failed");
        }

        List<Transfer> transfers = transferRepository.getTransfersByIban(iban);
        List<GetAccountActivitiesResponseDTO> accountActivities = new ArrayList<>();
        for (int i = 0; i < transfers.size(); i++){
            //if the user is the sending account
            Transfer transfer = transfers.get(i);
            if(transfer.getSenderIban().equals(iban)){
                GetAccountActivitiesResponseDTO getAccountActivitiesResponseDTO =
                        new GetAccountActivitiesResponseDTO(transfer.getSenderIban(),
                                                            transfer.getAmount().multiply(BigDecimal.valueOf(-1)),
                                                            transfer.getCurrency());
                    accountActivities.add(getAccountActivitiesResponseDTO);
            }
            else if(transfer.getReceiverIban().equals(iban)) {
                GetAccountActivitiesResponseDTO getAccountActivitiesResponseDTO =
                        new GetAccountActivitiesResponseDTO(transfer.getReceiverIban(),
                                                            transfer.getAmount(),
                                                            transfer.getCurrency());
                 accountActivities.add(getAccountActivitiesResponseDTO);
            }

        }
        return accountActivities;
    }
}
