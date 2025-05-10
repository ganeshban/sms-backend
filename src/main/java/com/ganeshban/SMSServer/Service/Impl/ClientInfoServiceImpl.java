package com.ganeshban.smsserver.service.impl;

import com.ganeshban.smsserver.DTO.ClientInfoDTO;
import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.entity.ClientInfoEntity;
import com.ganeshban.smsserver.repository.ClientInfoRepository;
import com.ganeshban.smsserver.Service.ClientInfoService;
import com.ganeshban.smsserver.model.ClientInfoModel;
import com.ganeshban.smsserver.transformer.ClientInfoTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.ganeshban.smsserver.utils.Constants.generatePassword;

@Service
@RequiredArgsConstructor
public class ClientInfoServiceImpl implements ClientInfoService {

    final private ClientInfoRepository repository;

    final private ClientInfoTransformer transformer;


    @Override
    @Cacheable(cacheNames = "clientInfo")
    public ClientInfoModel getClientInfoByClientCode(String code) throws NotFound {
        ClientInfoEntity entity = repository.findByClientCode(code).orElseThrow(() -> new NotFound("User not found with id code."));
        ClientInfoModel model = transformer.toModel(entity);
        return model;
    }

    public ClientInfoEntity getClientInfoEntityByClientCode(String code) throws NotFound {
        ClientInfoEntity entity = repository.findByClientCode(code).orElseThrow(() -> new NotFound("User not found with id code."));
        return entity;
    }

    @Override
    public ClientInfoModel save(ClientInfoDTO clientInfoDTO) {
        ClientInfoEntity entity = transformer.dtoToEntity(clientInfoDTO);
        entity.setClientCode(generateClientCode());


        return transformer.toModel(repository.save(entity));
    }

    private String generateClientCode() {
        return "SMS" + generatePassword(7, true);

    }
}
