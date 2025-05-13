package com.ganeshban.smsserver.service.impl;

import com.ganeshban.smsserver.DTO.LoginDTO;
import com.ganeshban.smsserver.entity.UserEntity;
import com.ganeshban.smsserver.model.UserModel;
import com.ganeshban.smsserver.repository.UserRepository;
import com.ganeshban.smsserver.transformer.UserTransformer;
import com.ganeshban.smsserver.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final UserTransformer userTransformer;
    private final JwtUtils jwtUtils;
    private final UserRepository repository;

    public UserModel doLogin(LoginDTO dto) {
        UserEntity user = repository.findByUserNameAndPassword(dto.getUsername(), dto.getPassword())
                .orElseThrow(() -> new UsernameNotFoundException("User not found for " + dto.getUsername()));
        UserModel model = userTransformer.toModel(user);
//        model.setSenders(Set.of("7207519966"));
        model.setJwtToken(jwtUtils.generateToken(user.getUserName()));
        return model;

    }
}
