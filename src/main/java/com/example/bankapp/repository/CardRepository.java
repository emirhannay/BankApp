package com.example.bankapp.repository;

import com.example.bankapp.entity.Card;
import com.example.bankapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CardRepository extends JpaRepository<Card,Long> {

    @Query(value = "SELECT * FROM CARD AS C WHERE C.CUSTOMER_ID = :customerId AND C.CARD_TYPE = 'CREDIT' ",nativeQuery = true)
    Card findByCustomerId(@Param("customerId") Long customerId);
}
