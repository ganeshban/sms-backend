package com.ganeshban.smsserver.transformer;

import com.ganeshban.smsserver.DTO.SMSDataDTO;
import com.ganeshban.smsserver.entity.SMSDataEntity;
import com.ganeshban.smsserver.model.SMSDataModel;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface SMSDataTransformer {
    SMSDataEntity dtoToEntity(SMSDataDTO dto);
    SMSDataModel toModel (SMSDataEntity entity);
}



