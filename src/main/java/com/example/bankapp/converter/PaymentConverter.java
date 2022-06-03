package com.example.bankapp.converter;

import com.example.bankapp.dto.request.PayRequestDTO;
import com.example.bankapp.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentConverter {

   public Payment toPayment(PayRequestDTO payRequestDTO){
       Payment payment = new Payment();
       payment.setAmount(payRequestDTO.amount());
       payment.setSenderCardNo(payRequestDTO.senderCardNo());
       payment.setReceiverIban(payRequestDTO.receiverIban());
       return payment;
   }
}
