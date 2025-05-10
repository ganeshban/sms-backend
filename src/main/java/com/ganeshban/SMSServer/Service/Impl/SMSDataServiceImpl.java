package com.ganeshban.smsserver.service.impl;

import com.ganeshban.smsserver.DTO.SMSDataDTO;
import com.ganeshban.smsserver.entity.SMSDataEntity;
import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.repository.SMSDataRepository;
import com.ganeshban.smsserver.Service.SmsDataService;
import com.ganeshban.smsserver.model.SMSDataModel;
import com.ganeshban.smsserver.transformer.SMSDataTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SMSDataServiceImpl implements SmsDataService {
    private final String NOT_FOUND_MESSAGE = "Record not found, Please try again";

    final private SMSDataRepository repo;

    final private ClientInfoServiceImpl clientInfoService;
    final private SMSDataTransformer transformer;

    @Override
    public SMSDataModel newMessage(SMSDataDTO request, String code) throws NotFound {
        clientInfoService.getClientInfoByClientCode(code);
        SMSDataEntity entity = transformer.dtoToEntity(request);
//        entity.setSender("");
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
    public SMSDataEntity getOne(String id, String code) throws NotFound {

        return repo.findByIdAndClientCode(id, code).orElseThrow(() -> new NotFound(NOT_FOUND_MESSAGE));
    }

    @Override
    public List<SMSDataEntity> findAllByCode(String code) {
        return repo.findByClientCode(code);
    }

    @Override
    public boolean markAsRead(String id, String code) throws NotFound {
        SMSDataEntity data = getOne(id, code);

        data.setSync(true);
        repo.save(data);
        return true;

    }

    @Override
    public boolean markAsSend(String id, String code) throws NotFound {
        SMSDataEntity data = getOne(id, code);

        data.setSent(true);
        data.setSentDateTime(LocalDateTime.now());
        repo.save(data);
        return true;
    }


    //    @Override
    //    public SMSDataEntity findByCode(String code) throws NotFound {
    //
    //        return repo
    //                .findTop1ByClientCodeAndIsSync(code, false).orElseThrow(() -> new NotFound(NOT_FOUND_MESSAGE));
    //    }


}
