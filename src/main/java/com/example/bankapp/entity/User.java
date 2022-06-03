package com.example.bankapp.entity;

import com.example.bankapp.entity.enums.UserStatus;
import com.example.bankapp.entity.enums.UserType;
import com.example.bankapp.model.BaseExtendedModel;
import com.example.bankapp.model.BaseModel;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@Table(name= "Users")
public class User extends BaseExtendedModel {

    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private UserStatus userStatus = UserStatus.ACTIVE;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

    public void lockUser() {
        this.userStatus = UserStatus.LOCKED;
    }
    public void unLockUser() {
        this.userStatus = UserStatus.ACTIVE;
    }
}
