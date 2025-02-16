package com.ganeshban.SMSServer.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity
@Table(name = "tblSmsData")
@Data
@EqualsAndHashCode(callSuper = true)
public class SMSDataEntity extends BaseEntity{
    @NotNull(message = "must need to provide a receiver")
    @Column(name = "receiver")
    private String receiver;

    @NotNull(message = "must need to provide a message")
    @Column(name = "message")
    private String message;

    @Column(name = "isSent")
    private boolean isSent;

    @Column(name = "isSync")
    private boolean isSync;

    @NotNull(message = "must need to provide a client Code")
    @Column(name = "clientCode")
    private String clientCode;

    @Column(name = "sentTS")
    private LocalDateTime sentDateTime;
}
