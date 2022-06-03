package com.example.bankapp;

import com.example.bankapp.entity.Role;
import com.example.bankapp.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@SpringBootTest
public class InitializeDataTest {

    @Autowired
    private RoleRepository roleRepository;


    @Test
    public void testLoadDataForTestCase() {
        List<Role> roles = roleRepository.findAll();
        Assertions.assertEquals(2,roles.size());
    }
}
