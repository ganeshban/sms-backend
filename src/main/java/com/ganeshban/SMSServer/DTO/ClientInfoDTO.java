package com.ganeshban.SMSServer.DTO;

import com.ganeshban.SMSServer.Entity.ClientInfo;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Data
public class ClientInfoDTO {
    @Length(max = 40)
    private String firstName;

    @Length(max = 40)
    private String lastName;

    @Length(max = 100, min = 3)
    @NotNull
    @Email
    private String email;

    @Length(max = 15, min = 10)
    @NotNull
    private String phone;

    @Length(max = 250)
    private String address;

    @Length(max = 250)
    private String companyName;

    @NotNull
    @Length(max = 55, min = 10)
    private String senderPhone;

    public ClientInfo toEntity() {
        ClientInfo c = new ClientInfo();
        c.setFirstName(this.getFirstName());
        c.setLastName(this.getLastName());
        c.setEmail(this.getEmail());
        c.setPhone(this.getPhone());
        c.setAddress(this.getAddress());
        c.setCompanyName(this.getCompanyName());
        c.setSenderPhone(this.getSenderPhone());
        c.setClientID(UUID.randomUUID().toString());
        c.setClientCode(UUID.randomUUID().toString());

        return c;
    }
}
