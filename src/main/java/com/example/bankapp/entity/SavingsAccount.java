package com.example.bankapp.entity;

import com.example.bankapp.entity.enums.Currency;
import com.example.bankapp.model.BaseExtendedModel;
import com.example.bankapp.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class SavingsAccount extends BaseModel {

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "savingsAccount")
    private Set<SavingsAccountMaturity> savingsAccountMaturity;

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;


}
