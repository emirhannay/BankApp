package com.example.bankapp.converter;

import com.example.bankapp.dto.request.PayRequestDTO;
import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Payment;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class PaymentConverter {

    private final AccountRepository accountRepository;

   public Payment toPayment(PayRequestDTO payRequestDTO){

       Account account = accountRepository.findByIban(payRequestDTO.receiverIban()).orElseThrow(
               () -> new BusinessServiceOperationException.AccountNotFoundException("Receiver iban not found")
       );

       Payment payment = new Payment();
       payment.setAmount(payRequestDTO.amount());
       payment.setSenderCardNo(payRequestDTO.senderCardNo());
       payment.setReceiverIban(payRequestDTO.receiverIban());
       payment.setTransferDate(new Date());
       payment.setShop(account.getCustomer().getUser().getName());
       return payment;
   }
}
