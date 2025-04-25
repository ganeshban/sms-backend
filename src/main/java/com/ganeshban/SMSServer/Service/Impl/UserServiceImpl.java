package com.ganeshban.SMSServer.Service.Impl;

import com.ganeshban.SMSServer.config.*;
import com.ganeshban.SMSServer.DTO.LoginDTO;
import com.ganeshban.SMSServer.Entity.UserEntity;
import com.ganeshban.SMSServer.Repository.UserRepository;
import com.ganeshban.SMSServer.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repo;

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
