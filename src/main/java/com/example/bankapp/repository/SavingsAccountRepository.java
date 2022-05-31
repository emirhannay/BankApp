package com.example.bankapp.repository;

import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount,Long> {
    @Query(value = "SELECT * FROM ACCOUNT AS A WHERE A.CUSTOMER_ID = :customerId AND A.BALANCE > '0'",nativeQuery = true)
    List<Account> getAccountsWithMoney(@Param("customerId") Long customerId);
}
