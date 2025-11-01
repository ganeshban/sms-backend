package com.ganeshban.smsserver.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.ganeshban.smsserver.utils.Constants.Priority;

@Entity
@Table(name = "SMS_DATA")
@Data
public class SMSDataEntity extends BaseEntity {

    @Column(name = "SEQ_NUM", unique = true, updatable = false, insertable = false)
    private long sequenceNumber;

    @Column(name = "RECEIVER")
    private String receiver;

    @Column(name = "SENDER")
    private String sender;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "PRIORITY")
    private Priority priority;

    @Column(name = "IS_SYNC")
    private boolean isSync;

    @Column(name = "CLIENT_CODE")
    private String clientCode;

    @Column(name = "SENT_TS")
    private LocalDateTime sentDateTime;

    @Column(name = "CREATED_BY")
    private String createBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode());
    }


}
