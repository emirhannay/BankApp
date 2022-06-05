package com.example.bankapp.entity;

import com.example.bankapp.entity.enums.Currency;
import com.example.bankapp.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

import java.util.Date;

@Entity
@Getter
@Setter
public class Payment extends BaseModel {

    private String senderCardNo;
    private String receiverIban;
    private BigDecimal amount;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date transferDate;
    @Enumerated(value = EnumType.STRING)
    private Currency currency;
    private String shop;


}
