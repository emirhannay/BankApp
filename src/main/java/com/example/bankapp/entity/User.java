package com.example.bankapp.entity;

import com.example.bankapp.entity.enums.UserStatus;
import com.example.bankapp.entity.enums.UserType;
import com.example.bankapp.model.BaseExtendedModel;
import com.example.bankapp.model.BaseModel;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class User extends BaseExtendedModel {

    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private UserStatus userStatus = UserStatus.ACTIVE;

    public void lockUser() {
        this.userStatus = UserStatus.LOCKED;
    }
    public void unLockUser() {
        this.userStatus = UserStatus.ACTIVE;
    }
}
