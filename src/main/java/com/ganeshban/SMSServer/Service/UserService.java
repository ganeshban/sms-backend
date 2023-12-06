package com.ganeshban.SMSServer.Service;

import com.ganeshban.SMSServer.Core.NotFound;
import com.ganeshban.SMSServer.DTO.LoginDTO;
import com.ganeshban.SMSServer.Entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity createUser(UserEntity request) throws NotFound;
    UserEntity getOne(Long id) throws NotFound;
    List<UserEntity> getAll();
    UserEntity updateUser(UserEntity request) throws NotFound;
    UserEntity login(LoginDTO request) throws NotFound;
}

