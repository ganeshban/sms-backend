package com.ganeshban.smsserver.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserModel extends BaseModel {
    private String userName;
    private String clientCode;
    private Set<RoleModel> userRoles;
    private Set<String> senders;
    private String jwtToken;

}
