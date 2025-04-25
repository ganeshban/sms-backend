package com.ganeshban.SMSServer.Service;

import com.ganeshban.SMSServer.config.NotFound;
import com.ganeshban.SMSServer.DTO.LoginDTO;
import com.ganeshban.SMSServer.Entity.UserEntity;

public interface UserService {
    UserEntity getOne(String id) throws NotFound;
    UserEntity login(LoginDTO request) throws NotFound;

}

