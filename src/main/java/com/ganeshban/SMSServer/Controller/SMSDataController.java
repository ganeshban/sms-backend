package com.ganeshban.SMSServer.Controller;


import com.ganeshban.SMSServer.Core.NotFound;
import com.ganeshban.SMSServer.Entity.SMSDataEntity;
import com.ganeshban.SMSServer.Service.Impl.SMSDataServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Valid
@RestController
@RequestMapping("/sms/{code}")
public class SMSDataController {
    @Autowired
    private SMSDataServiceImpl services;

    @GetMapping("/all")
    public List<SMSDataEntity> findAllByCode(@PathVariable String code) throws NotFound {
        return services.findAllByCode(code);
    }

    @PostMapping("/create")
    public SMSDataEntity create(@RequestBody @Valid SMSDataEntity request) throws NotFound {
        return services.newMessage(request);
    }


    @GetMapping
    public SMSDataEntity getMessage (@PathVariable String code) throws NotFound {
        return services.findByCode(code);
    }

    @GetMapping("/read/{id}")
    public boolean markAsRead(@PathVariable long id, @PathVariable String code) throws NotFound {
        return services.markAsRead(id, code);
    }


    @GetMapping("/send/{id}")
    public boolean markAsSend(@PathVariable long id, @PathVariable String code) throws NotFound {
        return services.markAsSend(id, code);
    }


}
