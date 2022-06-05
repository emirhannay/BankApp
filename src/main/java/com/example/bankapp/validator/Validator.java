package com.example.bankapp.validator;

import com.example.bankapp.exception.BaseValidationException;

public interface Validator<T> {

    void validate(T input) throws BaseValidationException;
}
