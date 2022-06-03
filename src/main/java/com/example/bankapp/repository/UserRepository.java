package com.example.bankapp.repository;

import com.example.bankapp.entity.Customer;
import com.example.bankapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);

    @Query(value = "SELECT * FROM `User` AS U WHERE U.ID = :id",nativeQuery = true)
    Optional<Customer> findByUserId(@Param("id") Long id);
}
