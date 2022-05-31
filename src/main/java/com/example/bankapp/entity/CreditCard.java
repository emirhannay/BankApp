package com.example.bankapp.entity;

import com.example.bankapp.entity.enums.CreditCardStatus;
import com.example.bankapp.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class CreditCard extends BaseModel {

    private BigDecimal cardLimit;
    private BigDecimal lastOutstandingBalance;
    private BigDecimal currentDebt;
    private BigDecimal availableLimit;
    @Temporal(value = TemporalType.DATE)
    private Date cutoffDate;
    @Temporal(value = TemporalType.DATE)
    private Date nextCutoffDate;
    @Temporal(value = TemporalType.DATE)
    private Date paymentDueDate;
    @Temporal(value = TemporalType.DATE)
    private Date nextPaymentDueDate;
    @OneToOne(cascade = CascadeType.ALL,optional = false)
    private Card card;
    @Enumerated(value = EnumType.STRING)
    private CreditCardStatus creditCardStatus;


}
