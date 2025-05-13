package com.ganeshban.smsserver.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserModel extends BaseModel {
    private String userName;
    private Set<RoleModel> roles;
    private String jwtToken;

}
