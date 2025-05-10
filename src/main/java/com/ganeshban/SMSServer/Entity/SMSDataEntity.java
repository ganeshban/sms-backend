package com.ganeshban.smsserver.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "SMS_DATA")
@Data
@EqualsAndHashCode(callSuper = true)
public class SMSDataEntity extends BaseEntity {

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return  super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }


}
