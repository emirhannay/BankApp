package com.example.bankapp.controller;


import com.example.bankapp.validator.AuthenticationRequestValidator;
import com.example.bankapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.bankapp.helper.JWTHelper;
import com.example.bankapp.model.AuthenticationRequest;
import com.example.bankapp.model.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationRequestValidator authenticationRequestValidator
            = new AuthenticationRequestValidator();

    private final AuthenticationManager authenticationManager;
    private final JWTHelper jwtHelper;
    private final UserRepository userRepository;

    @PostMapping(path = "/sign-in")
    public ResponseEntity<?> signIn(@RequestBody AuthenticationRequest authenticationRequest) {
        authenticationRequestValidator.validate(authenticationRequest);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtHelper.generate(authenticationRequest.getEmail());

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
    @GetMapping(path = "/refresh-token")
    public ResponseEntity<?> xyz() {

        return ResponseEntity.ok().build();
    }
}
