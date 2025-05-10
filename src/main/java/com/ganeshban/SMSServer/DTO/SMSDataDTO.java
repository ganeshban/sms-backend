package com.ganeshban.smsserver.DTO;

import com.ganeshban.smsserver.repository.ClientInfoRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class SMSDataDTO {
    @Autowired
    private ClientInfoRepository repository;

    @NotNull
    @NotBlank
    @Length(max = 256)
    private String message;

    @NotNull
    @Length(min = 10, max = 15)
    private String receiver;

    private String sender;

}
