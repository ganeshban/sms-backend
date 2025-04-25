package com.ganeshban.SMSServer.Controller;


import com.ganeshban.SMSServer.config.NotFound;
import com.ganeshban.SMSServer.DTO.SMSDataDTO;
import com.ganeshban.SMSServer.Entity.SMSDataEntity;
import com.ganeshban.SMSServer.Service.Impl.SMSDataServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Valid
@RestController
@RequestMapping("api/sms/{code}")
public class SMSDataController {
    @Autowired
    private SMSDataServiceImpl services;

    @GetMapping("/all")
    public List<SMSDataEntity> findAllByCode(@PathVariable String code) {
        return services.findAllByCode(code);
    }

    @PostMapping("/create")
    public SMSDataEntity createNewMessage(@RequestBody @Valid SMSDataDTO request) throws NotFound {
        return services.newMessage(request.toEntity());
    }


//    @GetMapping
//    public SMSDataEntity getMessage (@PathVariable String code) throws NotFound {
//        return services.findByCode(code);
//    }

    @GetMapping("/read/{id}")
    public boolean markAsRead(@PathVariable String id, @PathVariable String code) throws NotFound {
        return services.markAsRead(id, code);
    }


    @GetMapping("/send/{id}")
    public boolean markAsSend(@PathVariable String id, @PathVariable String code) throws NotFound {
        return services.markAsSend(id, code);
    }


}
