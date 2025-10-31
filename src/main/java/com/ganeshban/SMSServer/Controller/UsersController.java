package com.ganeshban.smsserver.controller;

import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.dto.LoginDTO;
import com.ganeshban.smsserver.entity.UserEntity;
import com.ganeshban.smsserver.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Valid
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UsersController {

    private final UserServiceImpl services;

    @GetMapping("/{id}")
    public UserEntity getOne(@PathVariable String id) throws NotFound {
        return services.getOne(id);
    }

    @PostMapping("/login")
    public UserEntity login(@RequestBody LoginDTO request) throws NotFound {
        return services.login((request));
    }
}







