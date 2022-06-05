package com.example.bankapp.repository;

import com.example.bankapp.entity.Customer;
import com.example.bankapp.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    @Query(value = "SELECT * FROM PAYMENT AS P WHERE P.SENDER_CARD_NO = :cardNo",nativeQuery = true)
    List<Payment> getPaymentsByCardNo(@Param("cardNo") String cardNo);
}
