package com.example.bankapp.service;

import com.example.bankapp.converter.UserConverter;
import com.example.bankapp.dto.request.CreateAdminRequestDTO;
import com.example.bankapp.entity.User;
import com.example.bankapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public void createAdmin(CreateAdminRequestDTO createAdminRequestDTO){
        User user = userConverter.toUser(createAdminRequestDTO);
    }

}
