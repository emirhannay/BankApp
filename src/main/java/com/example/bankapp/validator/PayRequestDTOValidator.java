package com.example.bankapp.validator;

import com.example.bankapp.dto.request.PayRequestDTO;
import com.example.bankapp.exception.BaseValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class PayRequestDTOValidator implements Validator<PayRequestDTO> {

    private final BaseValidator baseValidator;

    @Override
    public void validate(PayRequestDTO payRequestDTO) throws BaseValidationException {
                baseValidator.doesStringContainsSpaces(payRequestDTO.senderCardNo(),"The card number cannot contain spaces.");
                baseValidator.doesStringContainsSpaces(payRequestDTO.receiverIban(),"The receiver iban cannot contain spaces.");
                baseValidator.doesStringContainsSpaces(payRequestDTO.cvv(),"The cvv cannot contain spaces.");
                if(payRequestDTO.amount().compareTo(BigDecimal.ZERO) == -1  || payRequestDTO.amount().compareTo(BigDecimal.ZERO) == 0  ){
                    throw new BaseValidationException("Amount must be greater than 0");
                }

    }
}
