package com.ganeshban.smsserver.controller;

import com.ganeshban.smsserver.DTO.LoginDTO;
import com.ganeshban.smsserver.service.impl.LoginService;
import com.ganeshban.smsserver.model.UserModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Valid
@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;

    @PostMapping("/login")
    public UserModel login(@RequestBody LoginDTO request) {

        return service.doLogin(request);
    }
}
