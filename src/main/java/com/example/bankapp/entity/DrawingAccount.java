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
public class DrawingAccount extends BaseExtendedModel {

    @Column(length = 26)
    private String iban;

    private BigDecimal balance;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name ="customer_id",nullable = false)
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    @OneToOne(cascade = CascadeType.ALL)
    private BankCard bankCard;
}
