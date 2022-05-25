package com.example.bankapp.entity;

import com.example.bankapp.entity.enums.Currency;
import com.example.bankapp.model.BaseExtendedModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class SavingsAccount extends BaseExtendedModel {
    private String iban;
    private BigDecimal balance;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name ="customer_id",nullable = false)
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private Currency currency;

}
