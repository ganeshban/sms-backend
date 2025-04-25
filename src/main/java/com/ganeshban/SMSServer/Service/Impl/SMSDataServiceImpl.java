package com.ganeshban.SMSServer.Service.Impl;

import com.ganeshban.SMSServer.DTO.SMSDataDTO;
import com.ganeshban.SMSServer.config.NotFound;
import com.ganeshban.SMSServer.Entity.SMSDataEntity;
import com.ganeshban.SMSServer.Repository.SMSDataRepository;
import com.ganeshban.SMSServer.Service.SmsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SMSDataServiceImpl implements SmsDataService {
    private final String NOT_FOUND_MESSAGE = "Record not found, Please try again";

    @Autowired
    SMSDataRepository repo;


    @Autowired
    ClientInfoServiceImpl clientInfoService;

    @Override
    public SMSDataEntity newMessage(SMSDataEntity request) throws NotFound {
        clientInfoService.getClientInfoByClientCode(request.getClientCode());
        return repo.save(request);
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
