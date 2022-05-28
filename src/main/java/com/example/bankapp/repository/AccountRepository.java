package com.example.bankapp.repository;

import com.example.bankapp.entity.Account;
import com.example.bankapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {

    @Query(value = "SELECT * FROM ACCOUNT AS A WHERE A.IBAN = :iban",nativeQuery = true)
    Optional<Account> findByIban(@Param("iban") String iban);
}
