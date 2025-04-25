package com.ganeshban.SMSServer.Controller;

import com.ganeshban.SMSServer.config.NotFound;
import com.ganeshban.SMSServer.DTO.LoginDTO;
import com.ganeshban.SMSServer.Entity.UserEntity;
import com.ganeshban.SMSServer.Service.Impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Valid
@RestController
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UserServiceImpl services;

    @GetMapping("/{id}")
    public UserEntity getOne(@PathVariable String id) throws NotFound {
        return services.getOne(id);
    }

    @PostMapping("/login")
    public UserEntity login(@RequestBody LoginDTO request) throws NotFound {
        return services.login((request));
    }
}







