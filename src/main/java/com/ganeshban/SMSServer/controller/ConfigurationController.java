package com.ganeshban.smsserver.controller;

import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.dto.ConfigurationDTO;
import com.ganeshban.smsserver.model.ConfigurationModel;
import com.ganeshban.smsserver.service.ConfigurationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Valid
@RestController
@RequestMapping("/rest/config")
@RequiredArgsConstructor
public class ConfigurationController {
    private final ConfigurationService service;

    @PostMapping("save")
    public ConfigurationModel saveConfiguration(@RequestBody @Valid ConfigurationDTO config) {
        return service.saveConfig(config);
    }

    @GetMapping("get")
    public ConfigurationModel getConfiguration(@RequestBody @Valid ConfigurationDTO config) throws NotFound {
        return service.getConfigModel(config);
    }

    @DeleteMapping("delete")
    public void deleteConfiguration(@RequestBody @Valid ConfigurationDTO config) throws NotFound {
        service.deleteConfig(config);
    }
}
