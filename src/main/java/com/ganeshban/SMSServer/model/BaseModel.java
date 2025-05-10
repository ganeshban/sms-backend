package com.ganeshban.smsserver.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseModel {
    private String id;
    private LocalDateTime createdAt;
    private String status;
}
