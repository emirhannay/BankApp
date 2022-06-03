package com.example.bankapp.repository;

import com.example.bankapp.entity.DrawingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DrawingAccountRepository extends JpaRepository<DrawingAccount,Long> {

    @Query(value = "SELECT * FROM DRAWING_ACCOUNT AS D INNER JOIN ACCOUNT AS A ON A.ID = D.ACCOUNT_ID WHERE A.CUSTOMER_ID= :customerId ", nativeQuery = true)
    Optional<List<DrawingAccount>> findDrawingAccountsByCustomerId(@Param("customerId") Long customerId);

    @Query(value = "SELECT * FROM DRAWING_ACCOUNT AS D WHERE D.BANK_CARD_ID= :bankCardId ", nativeQuery = true)
    Optional<DrawingAccount> findDrawingAccountsByBankCardId(@Param("bankCardId") Long bankCardId);
}

