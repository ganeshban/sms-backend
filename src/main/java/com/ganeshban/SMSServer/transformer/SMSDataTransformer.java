package com.ganeshban.smsserver.transformer;

import com.ganeshban.smsserver.dto.SMSDataDTO;
import com.ganeshban.smsserver.entity.SMSDataEntity;
import com.ganeshban.smsserver.model.SMSDataModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.ganeshban.smsserver.utils.Constants.Keyword.MAPPER_NAME;


@Mapper(componentModel = MAPPER_NAME)
public interface SMSDataTransformer {
    @Mapping(target = "priority", defaultValue = "VERY_LOW")
    SMSDataEntity dtoToEntity(SMSDataDTO dto);
    SMSDataModel toModel (SMSDataEntity entity);
}



