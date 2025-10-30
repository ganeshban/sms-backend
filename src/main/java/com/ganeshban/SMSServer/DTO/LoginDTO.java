package com.ganeshban.smsserver.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class LoginDTO {
    @Length(min = 3)
    private String username;
    private String password;
}

