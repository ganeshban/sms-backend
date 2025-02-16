package com.ganeshban.SMSServer.Service;

import com.ganeshban.SMSServer.Core.NotFound;
import com.ganeshban.SMSServer.DTO.LoginDTO;
import com.ganeshban.SMSServer.Entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity getOne(String id) throws NotFound;
    UserEntity login(LoginDTO request) throws NotFound;

}

