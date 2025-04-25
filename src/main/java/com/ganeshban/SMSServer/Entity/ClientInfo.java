package com.ganeshban.SMSServer.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;




@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "CLIENT_INFO")
public class ClientInfo extends BaseEntity {
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

    @Column(name = "CLIENT_ID", unique = true)
    private String clientID;

    @Column(name = "CLIENT_SECRET")
    private String clientSecret;

    @Column(name = "CLIENT_CODE", unique = true)
    private String clientCode;

    @Column(name = "SENDER_PHONE")
    private String senderPhone;
}




