package com.ganeshban.smsserver.service;

import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.dto.SMSDataDTO;
import com.ganeshban.smsserver.model.SMSDataModel;
import com.ganeshban.smsserver.model.search.SearchRequest;

import java.util.List;

public interface SmsDataService {
    List<SMSDataModel> newMessage(SMSDataDTO request, String code) throws NotFound;

    List<SMSDataModel> newMessageInBulk(List<SMSDataDTO> requests, String code) throws NotFound;

//    List<SMSDataModel> findAllByCode(String code);

    List<SMSDataModel> findAllByCode(String code, SearchRequest request);

    List<SMSDataModel> findAllSendingMessage(String code, String sender);

    boolean markAsSend(List<String> ids, String code) throws NotFound;
}
