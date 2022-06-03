package com.example.bankapp.converter;

import com.example.bankapp.dto.request.CreateAdminRequestDTO;
import com.example.bankapp.entity.Role;
import com.example.bankapp.entity.User;
import com.example.bankapp.entity.enums.UserType;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class UserConverter {
    private final RoleRepository roleRepository;

    public User toUser(CreateAdminRequestDTO createAdminRequestDTO) {
        Role role = roleRepository.findByName("ADMIN").orElseThrow(
                () -> new BusinessServiceOperationException.CreateAdminFailedException("Create admin operation failed because role not found")
        );

        User user = new User();
        user.setUserType(UserType.ADMIN);
        user.setName(createAdminRequestDTO.name());
        user.setEmail(createAdminRequestDTO.email());
        user.setPassword(createAdminRequestDTO.password());
        user.setCreatedAt(new Date());
        user.setDeleted(false);
        user.setPhoneNumber(createAdminRequestDTO.phoneNumber());
        user.getRoles().add(role);

        return user;
    }
}
