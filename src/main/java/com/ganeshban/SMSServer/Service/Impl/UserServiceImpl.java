package com.ganeshban.SMSServer.Service.Impl;

import com.ganeshban.SMSServer.Core.NotFound;
import com.ganeshban.SMSServer.DTO.LoginDTO;
import com.ganeshban.SMSServer.Entity.UserEntity;
import com.ganeshban.SMSServer.Repository.UserRepository;
import com.ganeshban.SMSServer.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repo;

    @Override
    public UserEntity createUser(UserEntity request) throws NotFound{
        return save(request);
    }

    @Override
    public UserEntity getOne(Long id) throws NotFound {
        return repo.findById(id).orElseThrow(() -> new NotFound("user not found"));
    }

    @Override
    public List<UserEntity> getAll() {
        return repo.findAll();
    }


    private UserEntity save(UserEntity data) throws NotFound {

        Optional<UserEntity> user = repo.findByUserName(data.getUserName());
        if (user.isPresent()) throw new NotFound("username is not available");

        user = repo.findByClientCode(data.getClientCode());
        if (user.isPresent()) throw new NotFound("client code is not available");

        repo.save(data);
        return data;
    }

    @Override
    public UserEntity updateUser(UserEntity request) throws NotFound {
        return save(request);
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
