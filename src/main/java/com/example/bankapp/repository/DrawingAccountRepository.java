package com.example.bankapp.repository;

import com.example.bankapp.entity.DrawingAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DrawingAccountRepository extends JpaRepository<DrawingAccount,Long> {

    @Query(value = "select * from drawing_account AS d where d.customer_id= :customerId ", nativeQuery = true)
    DrawingAccount findDrawingAccountByCustomerId(@Param("customerId") Long customerId);
}
