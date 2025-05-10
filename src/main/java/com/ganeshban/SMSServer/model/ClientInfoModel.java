package com.ganeshban.smsserver.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClientInfoModel extends BaseModel {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String companyName;
    private String clientCode;
    private List<SenderModel> senders;
    private List<UserModel> userModels;
}
