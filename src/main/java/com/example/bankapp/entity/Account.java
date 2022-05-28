package com.example.bankapp.entity;

import com.example.bankapp.entity.enums.AccountType;
import com.example.bankapp.entity.enums.Currency;
import com.example.bankapp.model.BaseExtendedModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Account extends BaseExtendedModel {
    @Column(length = 26,nullable = false)
    private String iban;

    @Min(value = 0)
    @Column(nullable = false)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Currency currency;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name ="customer_id",nullable = false)
    private Customer customer;

    @OneToOne(mappedBy = "account",cascade = CascadeType.REMOVE)
    private DrawingAccount drawingAccount;
    @OneToOne(mappedBy = "account",cascade = CascadeType.REMOVE)
    private SavingsAccount savingsAccount;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType;
}
