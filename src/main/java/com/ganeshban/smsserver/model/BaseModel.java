package com.ganeshban.smsserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class BaseModel {
    private String id;
    private LocalDateTime createdAt;
    private String status;
}
