package com.ganeshban.smsserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

@Entity
@Table(name = "USER_ROLE")
public class UserRoleEntity extends BaseEntity {
    @JoinColumn(name = "USER_ID")
    @ManyToOne
    @JsonIgnore
    private UserEntity user;

    @JoinColumn(name = "ROLE_ID")
    @ManyToOne
    private RoleEntity role;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
