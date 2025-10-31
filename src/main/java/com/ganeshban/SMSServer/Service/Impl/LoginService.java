package com.ganeshban.smsserver.service.impl;

import com.ganeshban.smsserver.dto.LoginDTO;
import com.ganeshban.smsserver.dto.RefreshTokenDTO;
import com.ganeshban.smsserver.entity.UserEntity;
import com.ganeshban.smsserver.model.UserModel;
import com.ganeshban.smsserver.repository.UserRepository;
import com.ganeshban.smsserver.transformer.UserTransformer;
import com.ganeshban.smsserver.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.ganeshban.smsserver.utils.Constants.ErrorMessage.USER_NOT_FOUND_FOR;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserTransformer userTransformer;
    private final JwtUtils jwtUtils;
    private final UserRepository repository;

    public UserModel doLogin(LoginDTO dto) {
        UserEntity user = repository.findByUserNameAndPassword(dto.getUsername(), dto.getPassword())
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_FOR + dto.getUsername()));
        UserModel model = userTransformer.toModel(user);
        model.setJwtToken(jwtUtils.generateToken(user.getUserName()));
        return model;

    }

    public RefreshTokenDTO refreshToken(RefreshTokenDTO request) {
        var username = jwtUtils.extractUsername(request.getToken());
        return new RefreshTokenDTO(jwtUtils.generateToken(username));
    }
}
