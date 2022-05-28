package com.example.bankapp.entity;

import com.example.bankapp.entity.enums.Currency;
import com.example.bankapp.model.BaseExtendedModel;
import com.example.bankapp.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class DrawingAccount extends BaseModel {

    @OneToOne(cascade = CascadeType.ALL)
    private BankCard bankCard;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;
}
