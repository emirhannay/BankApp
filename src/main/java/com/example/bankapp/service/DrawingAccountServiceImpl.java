package com.example.bankapp.service;

import com.example.bankapp.converter.DrawingAccountConverter;
import com.example.bankapp.dto.response.GetCustomerResponseDTO;
import com.example.bankapp.dto.response.GetDrawingAccountResponseDTO;
import com.example.bankapp.entity.Customer;
import com.example.bankapp.entity.DrawingAccount;
import com.example.bankapp.entity.User;
import com.example.bankapp.exception.BaseException;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.helper.UserHelper;
import com.example.bankapp.repository.CustomerRepository;
import com.example.bankapp.repository.DrawingAccountRepository;
import com.example.bankapp.dto.request.CreateDrawingAccountRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class DrawingAccountServiceImpl implements DrawingAccountService{

    private final DrawingAccountRepository drawingAccountRepository;
    private final CustomerRepository customerRepository;
    private final DrawingAccountConverter drawingAccountConverter;
    private final UserHelper userHelper;

    @Override
    public void create(CreateDrawingAccountRequestDTO createDrawingAccountRequestDTO) {
        DrawingAccount drawingAccount = drawingAccountConverter.toDrawingAccount(createDrawingAccountRequestDTO);
        User loggedInUser = userHelper.getLoggedInUser();
        if( !(drawingAccount.getAccount().getCustomer().getUser() == loggedInUser) &  !(userHelper.isLoggedInUserAdmin()) ){
            throw new BusinessServiceOperationException.GetCustomerFailedException("Create drawing account failed");
        }
            drawingAccountRepository.save(drawingAccount);
            log.info("Drawing account was successfully created -> {}" ,drawingAccount.getId());

    }


    public List<GetDrawingAccountResponseDTO> getDrawingAccountsByCustomerId(Long customerId){
        Customer customer = customerRepository.findById(customerId).orElseThrow(
                () -> new BusinessServiceOperationException.CustomerNotFoundException("Customer not found")
        );
        User loggedInUser = userHelper.getLoggedInUser();
        if( !(customer.getUser() == loggedInUser) &&  !(userHelper.isLoggedInUserAdmin()) ){
            throw new BusinessServiceOperationException.GetCustomerFailedException("Get drawing accounts failed");
        }

        List<DrawingAccount> drawingAccount = drawingAccountRepository.findDrawingAccountsByCustomerId(customerId)
                .orElseThrow(
                        ()-> new BusinessServiceOperationException.DrawingAccountExistsException("Drawing account not found")
                );
        List<GetDrawingAccountResponseDTO> drawingAccounts = drawingAccount.stream().
                map(drawingAccountConverter::toGetDrawingAccountResponseDTO).toList();
        return drawingAccounts;
    }

    @Override
    public GetDrawingAccountResponseDTO getDrawingAccount(Long id) throws BaseException {
        DrawingAccount drawingAccount = drawingAccountRepository.findById(id).orElseThrow(
                () -> new BusinessServiceOperationException.DrawingAccountExistsException("Drawing account not found")
        );
        User loggedInUser = userHelper.getLoggedInUser();
        if( !(drawingAccount.getAccount().getCustomer().getUser() == loggedInUser) &&  !(userHelper.isLoggedInUserAdmin()) ){
            throw new BusinessServiceOperationException.GetCustomerFailedException("Get drawing account failed");
        }
        return drawingAccountConverter.toGetDrawingAccountResponseDTO(drawingAccount);
    }
}
