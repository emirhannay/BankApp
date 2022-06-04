package com.example.bankapp.repository;

import com.example.bankapp.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransferRepository extends JpaRepository<Transfer,Long> {

    @Query(value = "SELECT * FROM TRANSFER AS T WHERE T.SENDER_IBAN = :iban OR T.RECEIVER_IBAN =:iban",nativeQuery = true)
    List<Transfer> getTransfersByIban(@Param("iban") String iban);
}
