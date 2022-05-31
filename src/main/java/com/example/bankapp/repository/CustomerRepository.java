package com.example.bankapp.repository;

import com.example.bankapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM CUSTOMER AS C WHERE C.USER_ID = :userId",nativeQuery = true)
    Optional<Customer> findByUserId(@Param("userId") Long userId);


}
