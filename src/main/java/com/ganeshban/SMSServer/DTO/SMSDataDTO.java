package com.ganeshban.smsserver.dto;

import com.ganeshban.smsserver.repository.ClientInfoRepository;

import static com.ganeshban.smsserver.utils.Constants.Priority;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class SMSDataDTO {

    private final ClientInfoRepository repository;

    @NotNull
    @NotBlank
    @Length(max = 160)
    private String message;

    @NotNull
    @Length(min = 10, max = 15)
    private String receiver;
    private Priority priority;
    private String sender;

}
