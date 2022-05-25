package com.example.bankapp.repository;

import com.example.bankapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM CUSTOMER AS C WHERE C.USER_ID = :userId",nativeQuery = true)
    Customer findByUserId(@Param("userId") Long userId);
}
