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
    @Column(nullable = false,unique = true, length = 11)
    private String identityNo;

    @OneToMany(mappedBy = "customer", cascade =  CascadeType.REMOVE)
    private Set<Account> accounts;

    @OneToMany(mappedBy = "customer", cascade =  CascadeType.REMOVE)
    private Set<Card> cards;

}
