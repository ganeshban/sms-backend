package com.ganeshban.smsserver.service.impl;

import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.dto.ConfigurationDTO;
import com.ganeshban.smsserver.entity.ConfigurationEntity;
import com.ganeshban.smsserver.model.ConfigurationModel;
import com.ganeshban.smsserver.repository.ConfigurationRepository;
import com.ganeshban.smsserver.service.ConfigurationService;
import com.ganeshban.smsserver.transformer.ConfigTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfigurationServiceImpl implements ConfigurationService {
    private final ConfigurationRepository repository;
    private final ConfigTransformer transformer;

    public ConfigurationModel saveConfig(ConfigurationDTO request) {
        ConfigurationEntity entity;
        try {
            entity = getConfigEntity(request);
        } catch (NotFound e) {
            entity = transformer.toEntity(request);
        }
        ConfigurationEntity savedConfig = repository.save(entity);
        return transformer.toModel(savedConfig);

    }

    public ConfigurationModel getConfigModel(ConfigurationDTO request) throws NotFound {
        ConfigurationEntity entity = getConfigEntity(request);
        return transformer.toModel(entity);

    }

    public void deleteConfig(ConfigurationDTO request) throws NotFound {
        repository.delete(getConfigEntity(request));
    }

    @Override
    public List<ConfigurationModel> getConfigModelByClientCode(String code) {

        return repository.findByClientCode(code).stream().map(transformer::toModel).toList();
    }

    private ConfigurationEntity getConfigEntity(ConfigurationDTO request) throws NotFound {
        String clientCode = request.getClientCode();
        String senderDevice = request.getSenderDevice();
        return repository
                .findByClientCodeAndSenderDevice(clientCode, senderDevice)
                .orElseThrow(() -> new NotFound("Configuration not Found"));
    }
}
