package com.example.bankapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseExtendedModel extends BaseModel {

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date deletedAt;

    private boolean isDeleted;
}
