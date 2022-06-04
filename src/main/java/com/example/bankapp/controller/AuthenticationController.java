package com.example.bankapp.controller;


import com.example.bankapp.dto.request.CreateAdminRequestDTO;
import com.example.bankapp.dto.request.CreateCustomerRequestDTO;
import com.example.bankapp.entity.Customer;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.repository.CustomerRepository;
import com.example.bankapp.security.UserDetail;
import com.example.bankapp.service.CustomerService;
import com.example.bankapp.service.UserService;
import com.example.bankapp.validator.AuthenticationRequestValidator;
import com.example.bankapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.example.bankapp.helper.JWTHelper;
import com.example.bankapp.model.AuthenticationRequest;
import com.example.bankapp.model.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationRequestValidator authenticationRequestValidator
            = new AuthenticationRequestValidator();

    private final AuthenticationManager authenticationManager;
    private final JWTHelper jwtHelper;
    private final UserRepository userRepository;
    private final CustomerService customerService;
    private final UserService userService;
    private final CustomerRepository customerRepository;


    @PostMapping(path = "/sign-in")
    public ResponseEntity<?> signIn(@RequestBody AuthenticationRequest authenticationRequest) {
        authenticationRequestValidator.validate(authenticationRequest);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        Customer customer = customerRepository.findByEmail(authenticationRequest.getEmail());
        String token = "";
        if(Objects.isNull(customer)){
            token = jwtHelper.generate(authenticationRequest.getEmail(),
                    authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())
                    ,null,userDetail.getId());
        }
        else {
            token = jwtHelper.generate(authenticationRequest.getEmail(),
                    authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())
                    ,customer.getId(),userDetail.getId());
        }


        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
    @PostMapping("/register")
    public ResponseEntity<?> create(@RequestBody CreateCustomerRequestDTO createCustomerRequestDTO) {

        customerService.save(createCustomerRequestDTO);
        return ResponseEntity.ok().body("Customer is created successfully");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin")
    public ResponseEntity<?> createAdmin(@RequestBody CreateAdminRequestDTO createAdminRequestDTO) {
        userService.createAdmin(createAdminRequestDTO);
        return ResponseEntity.ok().body("Admin is created successfully");
    }
}
