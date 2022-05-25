package com.example.bankapp.entity;

import com.example.bankapp.model.BaseExtendedModel;
import com.example.bankapp.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Getter
@Setter
@Table
public class Customer extends BaseModel {


    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @Column(nullable = false)
    private String identityNo;

    @OneToMany(mappedBy = "customer")
    private Set<DrawingAccount> drawingAccounts;
    @OneToMany(mappedBy = "customer")
    private Set<SavingsAccount> savingsAccounts;

    @OneToMany(mappedBy = "customer")
    private Set<BankCard> bankCard;


    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lastSuccessfullyLoggedInTime;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lastFailureLoggedInTime;



}
