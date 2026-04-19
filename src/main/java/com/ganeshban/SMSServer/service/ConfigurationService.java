package com.ganeshban.smsserver.service;


import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.dto.ConfigurationDTO;
import com.ganeshban.smsserver.model.ConfigurationModel;

import java.util.List;

public interface ConfigurationService {
    ConfigurationModel saveConfig(ConfigurationDTO request);

    ConfigurationModel getConfigModel(ConfigurationDTO request) throws NotFound;

    void deleteConfig(ConfigurationDTO request) throws NotFound;

    List<ConfigurationModel> getConfigModelByClientCode(String code);
}
