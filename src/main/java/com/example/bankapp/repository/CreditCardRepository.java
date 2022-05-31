package com.example.bankapp.repository;

import com.example.bankapp.entity.CreditCard;
import com.example.bankapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {

    @Query(value = "SELECT * FROM CREDIT_CARDS AS C WHERE CREDIT_CARD_STATUS ='ACTIVE'", nativeQuery = true)
    List<CreditCard> getAllActiveCreditCards();
}
