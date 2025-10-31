package com.ganeshban.smsserver.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ganeshban.smsserver.utils.Constants.Priority;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;


@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class SMSDataModel extends BaseModel {
    private String receiver;
    private String message;
//    private Priority priority;
//    private boolean isSync;
//    private String clientCode;
//    private LocalDateTime sentDateTime;

}
