package com.example.bankapp.repository;

import com.example.bankapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM CUSTOMER AS C WHERE C.USER_ID = :userId",nativeQuery = true)
    Optional<Customer> findByUserId(@Param("userId") Long userId);

    @Query(value = "SELECT * FROM CUSTOMER AS C INNER JOIN USER AS U ON C.USER_ID = U.ID WHERE U.EMAIL = :email",nativeQuery = true)
    Customer findByEmail(@Param("email") String email);

    Customer findByIdentityNo(String identityNo);

    @Query(value = "SELECT * FROM CUSTOMER AS C INNER JOIN USERS AS U" +
                   " ON C.USER_ID = U.ID" +
                   " WHERE C.ID = :id AND U.USER_STATUS = 'ACTIVE' AND U.IS_DELETED = 'FALSE' ",nativeQuery = true)
    Optional<Customer> findByCustomerIdAndStatus(@Param("id") Long id);

    @Query(value = "SELECT * FROM CUSTOMER AS C INNER JOIN USERS AS U ON C.USER_ID = U.ID WHERE U.IS_DELETED = 'FALSE' AND U.USER_STATUS = 'ACTIVE'",nativeQuery = true)
    List<Customer> getAllByDeleteStatus();


}
