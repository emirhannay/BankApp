package com.example.bankapp.dto.request;

import com.example.bankapp.entity.enums.UserStatus;
import com.example.bankapp.entity.enums.UserType;

public record CreateAdminRequestDTO(String email,
                                    String password,
                                    String name,
                                    String phoneNumber
                                    ) {
}
