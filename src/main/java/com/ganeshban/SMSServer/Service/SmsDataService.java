package com.ganeshban.SMSServer.Service;

import com.ganeshban.SMSServer.Core.NotFound;
import com.ganeshban.SMSServer.DTO.LoginDTO;
import com.ganeshban.SMSServer.Entity.SMSDataEntity;
import com.ganeshban.SMSServer.Entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface SmsDataService {
    SMSDataEntity newMessage(SMSDataEntity request) throws NotFound;

    SMSDataEntity getOne(Long id, String code) throws NotFound;

    List<SMSDataEntity> findAllByCode(String code);
    SMSDataEntity findByCode(String code) throws NotFound;

    boolean markAsRead(Long id, String code) throws NotFound;

    boolean markAsSend(Long id, String code) throws NotFound;

}
