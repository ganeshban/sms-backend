package com.ganeshban.smsserver.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import static com.ganeshban.smsserver.utils.Constants.Keyword.AUTO;
import static com.ganeshban.smsserver.utils.Constants.Priority;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SMSDataDTO {

    @NotNull
    @NotBlank
    @Length(max = 160)
    private String message;

    @NotNull
    @Length(min = 10, max = 15)
    private String receiver;
    private Priority priority;
    private String sender = AUTO;

}
