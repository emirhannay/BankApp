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

    @Query(value = "SELECT * FROM ACCOUNT AS A WHERE A.ACCOUNT_TYPE = 'CORPORATE' ",nativeQuery = true)
    List<Account> getCorporateAccounts();

    @Query(value = "SELECT * FROM ACCOUNT AS A WHERE A.CUSTOMER_ID = :customerId",nativeQuery = true)
    List<Account> getAllAccountsByCustomerId(@Param("customerId") Long customerId);




    @Query(value = "SELECT * FROM ACCOUNT AS A WHERE A.CUSTOMER_ID = :customerId AND A.BALANCE > '0'",nativeQuery = true)
    List<Account> getAccountsWithMoney(@Param("customerId") Long customerId);

    @Query(value = "SELECT * FROM ACCOUNT AS A INNER JOIN DRAWING_ACCOUNT AS D ON A.ID = D.ACCOUNT_ID WHERE D.BANK_CARD_ID = :bankCardId ",nativeQuery = true)
    Optional<Account> getAccountByBankCardId(@Param("bankCardId") Long bankCardId);

    @Query(value = "SELECT * FROM ACCOUNT AS A WHERE A.IBAN = :iban AND A.ACCOUNT_TYPE = 'CORPORATE' ",nativeQuery = true)
    Optional<Account> getCorporateAccountByIban(@Param("iban") String iban);

}
