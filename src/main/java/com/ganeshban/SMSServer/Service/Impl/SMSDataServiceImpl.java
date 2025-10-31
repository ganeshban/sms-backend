package com.ganeshban.smsserver.service.impl;

import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.dto.SMSDataDTO;
import com.ganeshban.smsserver.entity.ClientInfoEntity;
import com.ganeshban.smsserver.entity.SMSDataEntity;
import com.ganeshban.smsserver.entity.SenderEntity;
import com.ganeshban.smsserver.model.SMSDataModel;
import com.ganeshban.smsserver.repository.SMSDataRepository;
import com.ganeshban.smsserver.service.SmsDataService;
import com.ganeshban.smsserver.transformer.SMSDataTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ganeshban.smsserver.utils.Constants.Keyword.ACTIVE;
import static com.ganeshban.smsserver.utils.Constants.Keyword.AUTO;
import static com.ganeshban.smsserver.utils.Constants.ErrorMessage.SENDER_NOT_REGISTER;

@RequiredArgsConstructor
@Service
public class SMSDataServiceImpl implements SmsDataService {
    private final SMSDataRepository repo;
    private final ClientInfoServiceImpl clientInfoService;
    private final SMSDataTransformer transformer;

    @Override
    public SMSDataModel newMessage(SMSDataDTO request, String code) throws NotFound {


        ClientInfoEntity clientInformation = clientInfoService.getClientInfoByClientCode(code);
        Set<String> allowedSender = clientInformation.getSenders()
                .stream()
                .filter(x -> x.getStatus().equals(ACTIVE))
                .map(SenderEntity::getPhone)
                .collect(Collectors.toSet());
        allowedSender.add(AUTO);
        if (!allowedSender.contains(request.getSender())) {
            throw new NotFound(SENDER_NOT_REGISTER);
        }
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
        List<SMSDataEntity> smsList = repo.findNewSMSByCodeAndSender(code, sender);
        smsList.forEach(x -> {
            x.setPriority(null);
            x.setClientCode(null);
            x.setStatus(null);
            x.setCreatedAt(null);
        });
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
