package com.ganeshban.smsserver.repository;

import com.ganeshban.smsserver.entity.SMSDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SMSDataRepository extends JpaRepository<SMSDataEntity, String> {
    List<SMSDataEntity> findByClientCode(String code);

    Optional<SMSDataEntity> findByIdAndClientCode(String id, String code);


}
