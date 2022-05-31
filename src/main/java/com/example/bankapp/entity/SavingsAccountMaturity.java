package com.example.bankapp.entity;

import com.example.bankapp.entity.enums.SavingsAccountMaturityStatus;
import com.example.bankapp.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class SavingsAccountMaturity extends BaseModel {
    @ManyToOne
    private SavingsAccount savingsAccount;
    private BigDecimal amount;
    private int month;
    @Enumerated(value = EnumType.STRING)
    private SavingsAccountMaturityStatus savingsAccountMaturityStatus;
    private BigDecimal moneyWithInterest;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Temporal(TemporalType.DATE)
    private Date startDate;
}
