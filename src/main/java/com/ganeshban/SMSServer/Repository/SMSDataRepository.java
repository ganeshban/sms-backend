package com.ganeshban.smsserver.repository;

import com.ganeshban.smsserver.entity.SMSDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SMSDataRepository extends JpaRepository<SMSDataEntity, String> {
    List<SMSDataEntity> findByClientCode(String code);

    @Query("Select a from SMSDataEntity a where id in ( :ids ) and clientCode = :code")
    List<SMSDataEntity> findAllByListOfIdAndCode(List<String> ids, String code);

    @Query("Select a from SMSDataEntity a where clientCode = :code and status='ACTIVE' and isSync = false order by priority ASC")
    List<SMSDataEntity> findAllActiveSMSByCode( String code);


}
