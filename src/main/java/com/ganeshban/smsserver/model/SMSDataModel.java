package com.ganeshban.smsserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class SMSDataModel extends BaseModel {
    private String receiver;
    private String message;
}
