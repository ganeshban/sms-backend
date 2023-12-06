package com.ganeshban.SMSServer.Controller;

import com.ganeshban.SMSServer.DTO.LoginDTO;
import com.ganeshban.SMSServer.Entity.UserEntity;
import com.ganeshban.SMSServer.Core.NotFound;
import com.ganeshban.SMSServer.Service.Impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Valid
@RestController
@RequestMapping("/user")
public class SMSUsersController {
    @Autowired
    private UserServiceImpl services;

    @GetMapping("/{id}")
    public UserEntity getOne(@PathVariable long id) throws NotFound {
        return services.getOne(id);
    }

    @GetMapping("/all")
    public List<UserEntity> getAll() {
        return services.getAll();
    }

    @PostMapping("/create")
    public UserEntity create(@RequestBody @Valid UserEntity request) throws NotFound {
        return services.createUser(request);
    }


    @PostMapping("/login")
    public UserEntity login(@RequestBody LoginDTO request) throws NotFound {

        return services.login((request));
    }


    @PutMapping("/update")
    public UserEntity update(@RequestBody @Valid UserEntity data) throws NotFound {
        return services.updateUser(data);
    }

}







