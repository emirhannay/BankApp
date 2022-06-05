package com.example.bankapp.repository;

import com.example.bankapp.entity.SavingsAccountMaturity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface SavingsAccountMaturityRepository extends JpaRepository<SavingsAccountMaturity,Long> {


    @Query(value = "SELECT * FROM SAVINGS_ACCOUNT_MATURITY AS A WHERE END_DATE < :date AND SAVINGS_ACCOUNT_MATURITY_STATUS = 'PENDING'",nativeQuery = true)
    List<SavingsAccountMaturity> getAllSavingsAccountMaturitiesByEndDateAndStatus(@Param("date") Date date);

    @Query(value = "SELECT * FROM SAVINGS_ACCOUNT_MATURITY AS S INNER JOIN SAVINGS_ACCOUNT AS U" +
                   " ON S.SAVINGS_ACCOUNT_ID = U.ID" +
                   " INNER JOIN ACCOUNT AS A " +
                   " ON U.ACCOUNT_ID = A.ID" +
                   " WHERE A.IBAN = :iban",nativeQuery = true)
    List<SavingsAccountMaturity> getAllSavingsAccountMaturitiesByIban(@Param("iban") String iban);
}
