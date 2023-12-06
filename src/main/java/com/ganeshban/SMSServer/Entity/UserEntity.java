package com.ganeshban.SMSServer.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tblUserSMS")
public class UserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Column(name = "userName", length = 50, unique = true)
    private String userName;
    @Column(name = "userPassword", length = 50)
    private String password;
    private String clientCode;
    private String serverURL;
    private String senderPhone;
}




