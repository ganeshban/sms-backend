package com.ganeshban.SMSServer.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tblUsers")
public class UserEntity  extends BaseEntity{
    @Column(name = "userName")
    private String userName;
    @Column(name = "userPassword")
    private String password;
    @Column(name = "userType")
    private String userType;
}




