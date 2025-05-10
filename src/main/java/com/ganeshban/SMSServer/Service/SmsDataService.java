package com.ganeshban.smsserver.Service;

import com.ganeshban.smsserver.DTO.SMSDataDTO;
import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.entity.SMSDataEntity;
import com.ganeshban.smsserver.model.SMSDataModel;

import java.util.List;

public interface SmsDataService {
    SMSDataModel newMessage(SMSDataDTO request, String code) throws NotFound;
    List<SMSDataModel> newMessageInBulk(List<SMSDataDTO> requests, String code) throws NotFound;

    SMSDataEntity getOne(String id, String code) throws NotFound;

    List<SMSDataEntity> findAllByCode(String code);
//    SMSDataEntity findByCode(String code) throws NotFound;

    boolean markAsRead(String id, String code) throws NotFound;

    boolean markAsSend(String id, String code) throws NotFound;

}
