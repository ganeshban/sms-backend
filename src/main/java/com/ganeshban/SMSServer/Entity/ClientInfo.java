package com.ganeshban.SMSServer.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;


@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "tblClientInfo")
public class ClientInfo extends BaseEntity {
    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "clientId")
    private String clientID = UUID.randomUUID().toString();

    @Column(name = "clientCode")
    private String clientCode;

    @Column(name = "senderPhone")
    private String senderPhone;
}




