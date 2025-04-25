package com.ganeshban.SMSServer.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity
@Table(name = "SMS_DATA")
@Data
@EqualsAndHashCode(callSuper = true)
public class SMSDataEntity extends BaseEntity{

    @Column(name = "RECEIVER")
    private String receiver;

    @Column(name = "SENDER")
    private String sender;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "IS_SENT")
    private boolean isSent;

    @Column(name = "IS_SYNC")
    private boolean isSync;

    @Column(name = "CLIENT_CODE")
    private String clientCode;

    @Column(name = "SENT_TS")
    private LocalDateTime sentDateTime;
}
