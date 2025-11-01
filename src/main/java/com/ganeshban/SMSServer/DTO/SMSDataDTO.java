package com.ganeshban.smsserver.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.ganeshban.smsserver.utils.Constants.Keyword.AUTO;
import static com.ganeshban.smsserver.utils.Constants.Priority;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class SMSDataDTO {

    private String message;
    private List<String> messages;

    @NotNull
    private String receiver;

    private Priority priority = Priority.VERY_LOW;
    private String sender = AUTO;

}
