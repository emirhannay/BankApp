package com.example.bankapp.repository;

import com.example.bankapp.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card,Long> {

    @Query(value = "SELECT * FROM CARD AS C WHERE C.CUSTOMER_ID = :customerId AND C.CARD_TYPE = 'CREDIT' ",nativeQuery = true)
    Card findCreditCardByCustomerId(@Param("customerId") Long customerId);
    @Query(value = "SELECT * FROM CARD AS C WHERE C.CARD_NO = :cardNo",nativeQuery = true)
    Optional<Card> findByCardNo(@Param("cardNo") String cardNo);

    @Query(value = "SELECT * FROM CARD AS C WHERE C.CUSTOMER_ID =:customerId ",nativeQuery = true)
    List<Card> getCardsByCustomerId(@Param("customerId") Long customerId);
}
