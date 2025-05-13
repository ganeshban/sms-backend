package com.ganeshban.smsserver.model;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SenderModel extends BaseModel{
    private String phone;
}
