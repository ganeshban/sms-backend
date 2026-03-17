package com.ganeshban.smsserver.transformer;

import com.ganeshban.smsserver.dto.SMSDataDTO;
import com.ganeshban.smsserver.entity.SMSDataEntity;
import com.ganeshban.smsserver.model.SMSDataModel;
import org.mapstruct.Mapper;

import static com.ganeshban.smsserver.utils.Constants.Keyword.MAPPER_NAME;


@Mapper(componentModel = MAPPER_NAME)
public interface SMSDataTransformer {
    SMSDataEntity dtoToEntity(SMSDataDTO dto);

    SMSDataModel toModel(SMSDataEntity entity);
}



