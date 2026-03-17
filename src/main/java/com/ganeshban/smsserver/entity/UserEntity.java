package com.ganeshban.smsserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "USERS")
public class UserEntity extends BaseEntity {

    @Column(name = "USER_NAME", unique = true)
    private String userName;

    @JsonIgnore
    @Column(name = "USER_PASSWORD")
    private String password;

    @ManyToOne
    @JoinColumn(name = "CLIENT_CODE", referencedColumnName = "CLIENT_CODE")
    @JsonIgnore
    private ClientInfoEntity clientCode;

    @OneToMany(mappedBy = "user")
    private Set<UserRoleEntity> roles;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}




