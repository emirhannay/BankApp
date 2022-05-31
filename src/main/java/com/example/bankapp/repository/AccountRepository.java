package com.example.bankapp.repository;

import com.example.bankapp.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

    @Query(value = "SELECT * FROM ACCOUNT AS A WHERE A.IBAN = :iban ",nativeQuery = true)
    Optional<Account> findByIban(@Param("iban") String iban);
    @Query(value = "SELECT * FROM ACCOUNT AS A WHERE A.CUSTOMER_ID = :customerId AND A.BALANCE > '0'",nativeQuery = true)
    List<Account> getAccountsWithMoney(@Param("customerId") Long customerId);
}
