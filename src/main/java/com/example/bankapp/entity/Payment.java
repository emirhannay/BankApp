package com.example.bankapp.entity;

import com.example.bankapp.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Payment extends BaseModel {

    private String senderCardNo;
    private String receiverIban;
    private BigDecimal amount;


}
