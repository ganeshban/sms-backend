package com.ganeshban.SMSServer.Repository;

import com.ganeshban.SMSServer.Entity.SMSDataEntity;
import com.ganeshban.SMSServer.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SMSDataRepository extends JpaRepository<SMSDataEntity, Long> {
    List<SMSDataEntity> findByClientCode(String code);
    Optional<SMSDataEntity> findTop1ByClientCodeAndIsSync(String code, boolean isSync);

}
