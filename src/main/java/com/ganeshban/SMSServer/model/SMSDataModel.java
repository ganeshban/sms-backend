package com.ganeshban.smsserver.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@Data
public class SMSDataModel extends BaseModel {
    private String receiver;
    private String sender;
    private String message;
    private boolean isSent;
    private boolean isSync;
    private String clientCode;
    private LocalDateTime sentDateTime;

}
