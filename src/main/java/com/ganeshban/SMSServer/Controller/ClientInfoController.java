package com.ganeshban.smsserver.controller;

import com.ganeshban.smsserver.DTO.ClientInfoDTO;
import com.ganeshban.smsserver.entity.ClientInfoEntity;
import com.ganeshban.smsserver.service.impl.ClientInfoServiceImpl;
import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.model.ClientInfoModel;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("{code}/model")
    public ClientInfoModel getClientInfo(@PathVariable String code) throws NotFound {
        return service.getClientInfoByClientCode(code);
    }


    @GetMapping("{code}")
    public ClientInfoEntity getClientInfoEntity(@PathVariable String code) throws NotFound {
        return service.getClientInfoEntityByClientCode(code);
    }
}
