package com.ganeshban.smsserver.controller;

import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.dto.ClientInfoDTO;
import com.ganeshban.smsserver.model.ClientInfoModel;
import com.ganeshban.smsserver.service.impl.ClientInfoServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Valid
@RestController
@RequestMapping("/rest/client")
@RequiredArgsConstructor
public class ClientInfoController {

    final ClientInfoServiceImpl service;

    @PostMapping("create")
    public ClientInfoModel createClientInfo(@RequestBody @Valid ClientInfoDTO clientInfo) {

        return service.save(clientInfo);

    }
    @GetMapping("/myInfo")
    public ClientInfoModel getClientInfoEntity() throws NotFound {
        return service.getClientInfoForCurrentlyLogedInUser();
    }
}
