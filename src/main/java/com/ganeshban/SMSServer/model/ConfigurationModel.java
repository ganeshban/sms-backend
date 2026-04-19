package com.ganeshban.smsserver.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data

public class ConfigurationModel extends BaseModel {
    private String senderPhoneNumber;
    private String senderDevice;
    private int refreshToken;
    private int fetchInterval;
    private int batchCount;
    private boolean sendDirect;
    private boolean fetchSMS;
}
