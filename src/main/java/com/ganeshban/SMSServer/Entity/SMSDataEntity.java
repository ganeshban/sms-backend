package com.ganeshban.SMSServer.Entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "tblSMSData")
@Data
public class SMSDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "must need to provide a receiver")
    private String receiver;
    @NotNull(message = "must need to provide a message")
//    @Max(value = 255, message = "length of message must be less then 255")
    private String message;
    private boolean isSent;
    private boolean isSync;

    @NotNull(message = "must need to provide a client Code")
    private String clientCode;
    private String sentDateTime;
}
