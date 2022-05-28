package com.example.bankapp.entity;

import com.example.bankapp.model.BaseModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Role extends BaseModel {
    private String name;

}
