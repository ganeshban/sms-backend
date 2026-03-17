package com.ganeshban.smsserver.service.impl;

import com.ganeshban.smsserver.dto.LoginDTO;
import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.entity.UserEntity;
import com.ganeshban.smsserver.repository.UserRepository;
import com.ganeshban.smsserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.ganeshban.smsserver.utils.Constants.ErrorMessage.INCORRECT_USERNAME_AND_PASSWORD;
import static com.ganeshban.smsserver.utils.Constants.ErrorMessage.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repo;

    @Override
    @Cacheable(cacheNames = "user")
    public UserEntity getOne(String id) throws NotFound {
        return repo.findById(id).orElseThrow(() -> new NotFound(USER_NOT_FOUND));
    }

    @Override
    public UserEntity login(LoginDTO request) throws NotFound {
        UserEntity user = repo.findByUserName(request.getUsername()).orElseThrow(() -> new NotFound(INCORRECT_USERNAME_AND_PASSWORD));
        if (!user.getPassword().equals(request.getPassword())) {
            throw new NotFound(INCORRECT_USERNAME_AND_PASSWORD);
        }
        return user;
    }


}
