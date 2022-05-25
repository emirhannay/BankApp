package com.example.bankapp.repository;

import com.example.bankapp.entity.SavingsAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount,Long> {
}
