package com.example.bankapp.entity;

import com.example.bankapp.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class Card extends BaseModel {

    private String cardNo;
    @Temporal(value = TemporalType.DATE)
    private Date expirationDate;
    private String cvv;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id",nullable = false)
    private Customer customer;
}
