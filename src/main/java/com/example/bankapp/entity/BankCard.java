package com.example.bankapp.entity;

import com.example.bankapp.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Setter
@Getter
public class BankCard extends BaseModel {


    @OneToOne(cascade = CascadeType.ALL,optional = false)
    private Card card;












}
