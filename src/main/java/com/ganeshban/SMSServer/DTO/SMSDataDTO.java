package com.ganeshban.SMSServer.DTO;

import com.ganeshban.SMSServer.Core.NotFound;
import com.ganeshban.SMSServer.Entity.SMSDataEntity;
import com.ganeshban.SMSServer.Repository.ClientInfoRepository;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class SMSDataDTO {
    @Autowired
    private ClientInfoRepository repository;


    @NotNull
    @Length(max = 20)
    private String clientCode;

    @NotNull
    private String message;

    @NotNull
    @Length(min = 10, max = 15)
    private String receiver;

    public SMSDataEntity toEntity() {
        SMSDataEntity smsData = new SMSDataEntity();
        smsData.setClientCode(this.getClientCode());
        smsData.setMessage(this.getMessage());
        smsData.setReceiver(this.getReceiver());
        smsData.setSentDateTime(null);
        smsData.setSync(false);
        smsData.setSent(false);
        return smsData;

    }
}
