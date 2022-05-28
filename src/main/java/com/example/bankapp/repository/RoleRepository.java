package com.example.bankapp.repository;

import com.example.bankapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query(value = "SELECT * FROM ROLE AS R WHERE R.name = :name",nativeQuery = true)
    Optional<Role> findByName(@Param("name") String name);
}
