package com.ganeshban.smsserver.controller;


import com.ganeshban.smsserver.DTO.SMSDataDTO;
import com.ganeshban.smsserver.entity.SMSDataEntity;
import com.ganeshban.smsserver.service.impl.SMSDataServiceImpl;
import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.model.SMSDataModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Valid
@RequiredArgsConstructor
@RestController
@RequestMapping("api/sms/{code}")
public class SMSDataController {

    private final SMSDataServiceImpl services;

    @GetMapping("/all")
    public List<SMSDataEntity> findAllByCode(@PathVariable String code) {
        return services.findAllByCode(code);
    }

    @PostMapping("/create")
    public SMSDataModel createNewMessage(@RequestBody @Valid SMSDataDTO request, @PathVariable String code) throws NotFound {
        return services.newMessage(request, code);
    }


    @GetMapping
    public List<SMSDataEntity> getMessage(@PathVariable String code) {
        return services.findAllByCode(code);
    }

    @GetMapping("/read/{id}")
    public boolean markAsRead(@PathVariable String id, @PathVariable String code) throws NotFound {
        return services.markAsRead(id, code);
    }


    @GetMapping("/send/{id}")
    public boolean markAsSend(@PathVariable String id, @PathVariable String code) throws NotFound {
        return services.markAsSend(id, code);
    }


}
