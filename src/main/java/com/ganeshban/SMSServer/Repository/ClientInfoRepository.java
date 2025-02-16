package com.ganeshban.SMSServer.Repository;

import com.ganeshban.SMSServer.Entity.ClientInfo;
import com.ganeshban.SMSServer.Entity.SMSDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientInfoRepository extends JpaRepository<ClientInfo, String> {
    Optional<ClientInfo> findByClientCode(String code);
}
