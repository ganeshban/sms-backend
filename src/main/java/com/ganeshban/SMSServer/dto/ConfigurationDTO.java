package com.ganeshban.smsserver.dto;

import lombok.Data;

@Data
public class ConfigurationDTO {
    private String senderPhoneNumber;
    private String senderDevice;
    private int refreshToken;
    private int fetchInterval;
    private int batchCount;
    private boolean sendDirect;
    private boolean fetchSMS;
    private String clientCode;
}
