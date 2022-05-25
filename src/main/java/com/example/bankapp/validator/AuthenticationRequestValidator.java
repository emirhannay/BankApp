package com.example.bankapp.validator;


import com.example.bankapp.model.AuthenticationRequest;
import org.springframework.util.StringUtils;

import java.util.Objects;

public class AuthenticationRequestValidator {

    public void validate(AuthenticationRequest authenticationRequest) {
        if (Objects.isNull(authenticationRequest)) {
            throw new IllegalArgumentException("AuthenticationRequest can't be null");
        }
        if (!(StringUtils.hasLength(authenticationRequest.getEmail()))) {
            throw new IllegalArgumentException("Username can't be null");
        }
        if (!(StringUtils.hasLength(authenticationRequest.getPassword()))) {
            throw new IllegalArgumentException("Password¬ can't be null");
        }
    }
}
