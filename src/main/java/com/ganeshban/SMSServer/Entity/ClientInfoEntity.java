package com.ganeshban.smsserver.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Set;



@Data
@Entity
@Table(name = "CLIENT_INFO")

public class ClientInfoEntity extends BaseEntity {
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "CLIENT_CODE", unique = true)
    private String clientCode;

    @OneToMany(mappedBy = "clientCode")
    private Set<SenderEntity> sender;

    @OneToMany(mappedBy = "clientCode")
    private Set<UserEntity> userEntityList;


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}




