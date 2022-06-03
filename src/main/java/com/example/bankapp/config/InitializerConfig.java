package com.example.bankapp.config;

import com.example.bankapp.core.utils.CreatorService;
import com.example.bankapp.entity.*;
import com.example.bankapp.entity.enums.*;
import com.example.bankapp.exception.BusinessServiceOperationException;
import com.example.bankapp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;

@Configuration
@RequiredArgsConstructor
public class InitializerConfig {

    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final CreatorService creatorService;
    private final DrawingAccountRepository drawingAccountRepository;
    private final UserRepository userRepository;

    @PostConstruct
    public void onInit() {
       /* Role role = new Role();
        role.setName("ADMIN");
        Role role2 = new Role();
        role2.setName("CUSTOMER");
        Role persistedRole = roleRepository.save(role);
        roleRepository.save(role2);

        User user = new User();
        user.setUserStatus(UserStatus.ACTIVE);
        user.setUserType(UserType.ADMIN);
        user.setPhoneNumber("05365785476");
        user.setPassword(bCryptPasswordEncoder.encode("123456789e"));
        user.setEmail("emirhan@gmail.com");
        user.setName("Emirhan Ay");
        user.getRoles().add(persistedRole);
        user.setCreatedAt(new Date());
        userRepository.save(user);
       */
    }
}
