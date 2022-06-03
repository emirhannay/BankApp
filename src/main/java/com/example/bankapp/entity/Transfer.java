package com.example.bankapp.entity;

import com.example.bankapp.entity.enums.Currency;
import com.example.bankapp.entity.enums.TransferType;
import com.example.bankapp.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class Transfer extends BaseModel {

    @Column(nullable = false)
    private String senderIban;
    @Column(nullable = false)
    private String receiverIban;
    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date transferDate;
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private Currency currency;
    private String description;
}
