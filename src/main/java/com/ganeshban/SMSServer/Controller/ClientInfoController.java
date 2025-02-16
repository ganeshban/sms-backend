package com.ganeshban.SMSServer.Controller;

import com.ganeshban.SMSServer.DTO.ClientInfoDTO;
import com.ganeshban.SMSServer.Entity.ClientInfo;
import com.ganeshban.SMSServer.Service.Impl.ClientInfoServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Valid
@RestController
@RequestMapping("/rest/client")
public class ClientInfoController {

   @Autowired
   ClientInfoServiceImpl service;
   @PostMapping("create")
    ClientInfo createUsers(@RequestBody @Valid ClientInfoDTO clientInfo){

       return service.save(clientInfo.toEntity());
    }
}
