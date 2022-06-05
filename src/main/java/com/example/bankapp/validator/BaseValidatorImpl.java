package com.example.bankapp.validator;


import com.example.bankapp.exception.BaseValidationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class BaseValidatorImpl implements BaseValidator{

    public void doesStringContainsSpaces(String input,String message){
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(input);
        boolean found = matcher.find();
        if(found == true){
            throw new BaseValidationException(message);
        }
    };
    public void isTheStringNullOrEmpty(String input,String message){
        if(!(StringUtils.hasText(input))){
            throw new BaseValidationException(message);
        }
    }

}
