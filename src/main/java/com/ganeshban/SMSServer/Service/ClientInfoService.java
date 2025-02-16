package com.ganeshban.SMSServer.Service;

import com.ganeshban.SMSServer.Core.NotFound;
import com.ganeshban.SMSServer.Entity.ClientInfo;

public interface ClientInfoService {
    ClientInfo getClientInfoByClientCode(String code) throws NotFound;
    ClientInfo save(ClientInfo clientInfo);
}
