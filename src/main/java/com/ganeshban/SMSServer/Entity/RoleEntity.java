package com.ganeshban.smsserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "ROLES")
public class RoleEntity  extends BaseEntity{
    @Column(name = "ROLE_NAME")
    private String roleName;
    @Column(name = "ROLE_CODE", unique = true)
    private String roleDesc;
    @Column(name = "ROLE_DESC")
    private String roleCode;

    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private Set<UserRoleEntity> userRoles;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}
