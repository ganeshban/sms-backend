package com.ganeshban.smsserver.service.impl;

import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.dto.SMSDataDTO;
import com.ganeshban.smsserver.entity.SMSDataEntity;
import com.ganeshban.smsserver.model.SMSDataModel;
import com.ganeshban.smsserver.repository.SMSDataRepository;
import com.ganeshban.smsserver.service.SmsDataService;
import com.ganeshban.smsserver.transformer.SMSDataTransformer;
import com.ganeshban.smsserver.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SMSDataServiceImpl implements SmsDataService {
    private static final String NOT_FOUND_MESSAGE = "Record not found, Please try again";

    private final SMSDataRepository repo;
    private final ClientInfoServiceImpl clientInfoService;
    private final SMSDataTransformer transformer;

    @Override
    public SMSDataModel newMessage(SMSDataDTO request, String code) throws NotFound {
        clientInfoService.getClientInfoByClientCode(code);
        SMSDataEntity entity = transformer.dtoToEntity(request);
        entity.setClientCode(code);
        return transformer.toModel(repo.save(entity));
    }

    @Override
    public List<SMSDataModel> newMessageInBulk(List<SMSDataDTO> requests, String code) throws NotFound {
        clientInfoService.getClientInfoByClientCode(code);
        List<SMSDataEntity> requestEntities = requests.stream().map(transformer::dtoToEntity).toList();
        requestEntities.forEach(a -> a.setClientCode(code));
        List<SMSDataEntity> savedEntities = repo.saveAll(requestEntities);
        return savedEntities.stream().map(transformer::toModel).toList();

    }

    @Override
    public List<SMSDataModel> findAllByCode(String code) {
        List<SMSDataEntity> entities = repo.findByClientCode(code);
        return entities.stream().map(transformer::toModel).toList();

    }

    @Override
    public List<SMSDataModel> findAllSendingMessage(String code, String sender) {
        List<SMSDataEntity> smsList = repo.findAllActiveSMSByCode(code);
        return smsList.stream().map(transformer::toModel).toList();
    }


    @Override
    public boolean markAsSend(List<String> ids, String code) {
        List<SMSDataEntity> smsList = repo.findAllByListOfIdAndCode(ids, code);
        smsList.forEach(sms -> {
            sms.setSync(true);
            sms.setSentDateTime(LocalDateTime.now());
        });
        repo.saveAll(smsList);
        return true;
    }


}
