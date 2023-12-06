package com.ganeshban.SMSServer.Service.Impl;

import com.ganeshban.SMSServer.Core.NotFound;
import com.ganeshban.SMSServer.Entity.SMSDataEntity;
import com.ganeshban.SMSServer.Entity.UserEntity;
import com.ganeshban.SMSServer.Repository.SMSDataRepository;
import com.ganeshban.SMSServer.Repository.UserRepository;
import com.ganeshban.SMSServer.Service.SmsDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SMSDataServiceImpl implements SmsDataService {

    @Autowired
    SMSDataRepository repo;


    @Autowired
    UserRepository userRepo;

    @Override
    public SMSDataEntity newMessage(SMSDataEntity request) throws NotFound {
        userRepo.findByClientCode(request.getClientCode()).orElseThrow(() -> new NotFound("user not found"));
        SMSDataEntity data = new SMSDataEntity();
        data.setMessage(request.getMessage());
        data.setReceiver(request.getReceiver());
        data.setClientCode(request.getClientCode());
        repo.save(data);
        return data;
    }

    @Override
    public SMSDataEntity getOne(Long id, String code) throws NotFound {

        SMSDataEntity data = repo.findById(id).orElseThrow(() -> new NotFound("data not found"));
        if (!data.getClientCode().equals(code)) {
            throw new NotFound("you are not authorized to access this.");
        }
        return data;
    }

    @Override
    public List<SMSDataEntity> findAllByCode(String code) {
        return repo.findByClientCode(code);
    }

    @Override
    public boolean markAsRead(Long id, String code) throws NotFound {
        SMSDataEntity data = getOne(id, code);

        data.setSync(true);
        repo.save(data);
        return true;

    }

    @Override
    public boolean markAsSend(Long id, String code) throws NotFound {
        SMSDataEntity data = getOne(id, code);

        data.setSent(true);
        data.setSentDateTime(LocalDateTime.now().toString());
        repo.save(data);
        return true;
    }


    @Override
    public SMSDataEntity findByCode(String code) throws NotFound {

        return repo
                .findTop1ByClientCodeAndIsSync(code, false).orElseThrow(() -> new NotFound("data not found"));
    }

}
