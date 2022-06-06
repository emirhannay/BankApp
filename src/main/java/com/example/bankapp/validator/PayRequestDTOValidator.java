package com.example.bankapp.validator;

import com.example.bankapp.dto.request.PayRequestDTO;
import com.example.bankapp.exception.BaseValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PayRequestDTOValidator implements Validator<PayRequestDTO> {

    private final BaseValidator baseValidator;

    @Override
    public void validate(PayRequestDTO payRequestDTO) throws BaseValidationException {
            baseValidator.isTheStringNullOrEmpty(payRequestDTO.senderCardNo(),"Sender Card No");
            baseValidator.isTheStringNullOrEmpty(payRequestDTO.receiverIban(),"Receiver Iban");
            baseValidator.isTheStringNullOrEmpty(payRequestDTO.cvv(),"Cvv");


            baseValidator.doesStringContainsSpaces(payRequestDTO.senderCardNo(),"Sender Card No");
            baseValidator.doesStringContainsSpaces(payRequestDTO.receiverIban(),"Receiver Iban");
            baseValidator.doesStringContainsSpaces(payRequestDTO.cvv(),"Cvv");

            baseValidator.isZeroOrLessThanZeroOrNull(payRequestDTO.amount(),"Amount");
    }
}
