package com.ganeshban.smsserver.service.impl;

import com.ganeshban.smsserver.DTO.LoginDTO;
import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.entity.UserEntity;
import com.ganeshban.smsserver.repository.UserRepository;
import com.ganeshban.smsserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repo;

    @Override
    @Cacheable(cacheNames = "user")
    public UserEntity getOne(String id) throws NotFound {
        return repo.findById(id).orElseThrow(() -> new NotFound("user not found"));
    }

    @Override
    public UserEntity login(LoginDTO request) throws NotFound {
        String msg = "username and password are incorrect.";
        UserEntity user = repo.findByUserName(request.getUsername()).orElseThrow(() -> new NotFound(msg));
        if (!user.getPassword().equals(request.getPassword())) {
            throw new NotFound(msg);
        }
        return user;
    }


}
