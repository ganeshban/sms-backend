package com.ganeshban.smsserver.controller;

import com.ganeshban.smsserver.dto.LoginDTO;
import com.ganeshban.smsserver.dto.RefreshTokenDTO;
import com.ganeshban.smsserver.model.UserModel;
import com.ganeshban.smsserver.service.impl.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Valid
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class LoginController {

    private final LoginService service;

    @PostMapping("/login")
    public UserModel login(@RequestBody LoginDTO request) {

        return service.doLogin(request);
    }

    @PostMapping("/refresh-token")
    public RefreshTokenDTO refreshToken(@RequestBody RefreshTokenDTO request) {

        return service.refreshToken(request);
    }

    @GetMapping("/ping")
    public String ping() {
        log.info("ping success");
        return "Server is up";
    }


}
