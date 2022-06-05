package com.example.bankapp.validator;

import java.math.BigDecimal;

public interface BaseValidator {
    void doesStringContainsSpaces(String input,String inputName);
    void isTheStringNullOrEmpty(String input,String inputName);
    void isZeroOrLessThanZero(BigDecimal input, String inputName);
}
