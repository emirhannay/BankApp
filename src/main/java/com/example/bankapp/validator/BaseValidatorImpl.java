package com.example.bankapp.validator;


import com.example.bankapp.exception.BaseValidationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class BaseValidatorImpl implements BaseValidator{

    public void doesStringContainsSpaces(String input,String inputName){

        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(input);
        boolean found = matcher.find();
        if(found == true){
            throw new BaseValidationException("The "+" cannot contain spaces.");
        }
    };
    public void isTheStringNullOrEmpty(String input,String inputName){
        if(!(StringUtils.hasText(input))){
            throw new BaseValidationException(inputName+" can not be null or empty");
        }
    }
    public void isZeroOrLessThanZero(BigDecimal input, String inputName){
        if(input.compareTo(BigDecimal.ZERO) == -1  || input.compareTo(BigDecimal.ZERO) == 0  ){
            throw new BaseValidationException(inputName+" must be greater than 0");
        }
    }

}
