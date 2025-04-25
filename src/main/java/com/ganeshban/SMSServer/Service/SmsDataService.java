package com.ganeshban.SMSServer.Service;

import com.ganeshban.SMSServer.DTO.SMSDataDTO;
import com.ganeshban.SMSServer.config.NotFound;
import com.ganeshban.SMSServer.Entity.SMSDataEntity;

import java.util.List;

public interface SmsDataService {
    SMSDataEntity newMessage(SMSDataEntity request) throws NotFound;

    SMSDataEntity getOne(String id, String code) throws NotFound;

    List<SMSDataEntity> findAllByCode(String code);
//    SMSDataEntity findByCode(String code) throws NotFound;

    boolean markAsRead(String id, String code) throws NotFound;

    boolean markAsSend(String id, String code) throws NotFound;

}
