package com.ganeshban.smsserver.service;

import com.ganeshban.smsserver.DTO.ClientInfoDTO;
import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.model.ClientInfoModel;

public interface ClientInfoService {
    ClientInfoModel getClientInfoByClientCode(String code) throws NotFound;
    ClientInfoModel save(ClientInfoDTO clientInfoEntity);
}
