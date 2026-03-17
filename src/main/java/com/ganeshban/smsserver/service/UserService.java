package com.ganeshban.smsserver.service;

import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.dto.LoginDTO;
import com.ganeshban.smsserver.entity.UserEntity;

public interface UserService {
    UserEntity getOne(String id) throws NotFound;
    UserEntity login(LoginDTO request) throws NotFound;

}

