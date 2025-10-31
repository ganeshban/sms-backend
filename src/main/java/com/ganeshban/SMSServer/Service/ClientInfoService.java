package com.ganeshban.smsserver.service;

import com.ganeshban.smsserver.dto.ClientInfoDTO;
import com.ganeshban.smsserver.config.NotFound;
import com.ganeshban.smsserver.entity.ClientInfoEntity;
import com.ganeshban.smsserver.model.ClientInfoModel;

public interface ClientInfoService {
    ClientInfoEntity getClientInfoByClientCode(String code) throws NotFound;

    ClientInfoModel save(ClientInfoDTO clientInfoEntity);
}
