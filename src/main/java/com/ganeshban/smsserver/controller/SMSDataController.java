package com.ganeshban.smsserver.controller;


import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.dto.SMSDataDTO;
import com.ganeshban.smsserver.model.SMSDataModel;
import com.ganeshban.smsserver.service.impl.SMSDataServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Valid
@RequiredArgsConstructor
@RestController
@RequestMapping("api/sms/{code}")
public class SMSDataController {

    private final SMSDataServiceImpl services;

    @GetMapping("/all")
    public List<SMSDataModel> findAllByCode(@PathVariable String code) {
        return services.findAllByCode(code);
    }

    @PostMapping("/create")
    public List<SMSDataModel> createNewMessage(@RequestBody @Valid SMSDataDTO request, @PathVariable String code) throws NotFound {
        return services.newMessage(request, code);
    }

    @PostMapping("/create/bulk")
    public List<SMSDataModel> createNewMessage(@RequestBody @Valid List<SMSDataDTO> request, @PathVariable String code) throws NotFound {
        return services.newMessageInBulk(request, code);
    }

    @GetMapping("/{phone}")
    public List<SMSDataModel> getMessage(@PathVariable String phone, @PathVariable String code, @RequestParam(name = "hold", required = false, defaultValue = "false") boolean hold) {
        return services.findAllSendingMessage(code, phone, hold);
    }


    @PostMapping("/send")
    public boolean markAsSend(@RequestBody List<String> ids, @PathVariable String code) {
        return services.markAsSend(ids, code);
    }


}
