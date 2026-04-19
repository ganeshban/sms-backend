package com.ganeshban.smsserver.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "CONFIGURATIONS")
public class ConfigurationEntity extends BaseEntity {
    @Column(name = "SENDER_PHONE")
    private String senderPhoneNumber;
    @Column(name = "SENDER_DEVICE")
    private String senderDevice;
    @Column(name = "REFRESH_TOKEN")
    private int refreshToken;
    @Column(name = "FETCH_INTERVAL")
    private int fetchInterval;
    @Column(name = "BATCH_COUNT")
    private int batchCount;
    @Column(name = "SEND")
    private boolean sendDirect;
    @Column(name = "`FETCH`")
    private boolean fetchSMS;
    @Column(name = "CLIENT_CODE")
    private String clientCode;


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}
