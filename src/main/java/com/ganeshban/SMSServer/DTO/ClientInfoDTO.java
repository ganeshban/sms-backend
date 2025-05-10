package com.ganeshban.smsserver.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Data
public class ClientInfoDTO {
    @Length(max = 40)
    private String firstName;

    @Length(max = 40)
    private String lastName;

    @Length(max = 100, min = 3)
    @NotNull
    @Email
    @NotBlank
    private String email;

    @Length(max = 15, min = 10)
    @NotNull
    @NotBlank
    private String phone;

    @Length(max = 250)
    private String address;

    @Length(max = 250)
    private String companyName;

    private List<String> senderList;

}
