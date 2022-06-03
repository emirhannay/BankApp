package com.example.bankapp.repository;

import com.example.bankapp.entity.BankCard;
import com.example.bankapp.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BankCardRepository extends JpaRepository<BankCard,Long> {

    @Query(value = "SELECT * FROM BANK_CARD AS C WHERE C.CARD_ID = :cardId",nativeQuery = true)
    Optional<BankCard> findByCardId(@Param("cardId") Long cardId);
}
