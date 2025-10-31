package com.ganeshban.smsserver.repository;

import com.ganeshban.smsserver.entity.SMSDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ganeshban.smsserver.utils.Constants.SqlQueries.NEW_SMS_DATA_BY_CODE;
import static com.ganeshban.smsserver.utils.Constants.SqlQueries.NEW_SMS_DATA_BY_CODE_AND_SENDER;
import static com.ganeshban.smsserver.utils.Constants.SqlQueries.SMS_DATA_BY_ID_AND_CODE;

@Repository
public interface SMSDataRepository extends JpaRepository<SMSDataEntity, String> {

    List<SMSDataEntity> findByClientCode(String code);

    @Query(SMS_DATA_BY_ID_AND_CODE)
    List<SMSDataEntity> findAllByListOfIdAndCode(List<String> ids, String code);

    @Query(NEW_SMS_DATA_BY_CODE)
    List<SMSDataEntity> findAllActiveSMSByCode(String code);

    @Query(NEW_SMS_DATA_BY_CODE_AND_SENDER)
    List<SMSDataEntity> findNewSMSByCodeAndSender(String code, String sender);


}
