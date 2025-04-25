package com.ganeshban.SMSServer.Service.Impl;

import com.ganeshban.SMSServer.config.NotFound;
import com.ganeshban.SMSServer.Entity.ClientInfo;
import com.ganeshban.SMSServer.Repository.ClientInfoRepository;
import com.ganeshban.SMSServer.Service.ClientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ClientInfoServiceImpl implements ClientInfoService {
    @Autowired
    private ClientInfoRepository repository;


    @Override
    @Cacheable(cacheNames = "clientInfo")
    public ClientInfo getClientInfoByClientCode(String code) throws NotFound {
        return repository.findByClientCode(code).orElseThrow(()->new NotFound("User not found with id code."));
    }

    @Override
    public ClientInfo save(ClientInfo clientInfo) {
        return repository.save(clientInfo);
    }
}
