package com.ganeshban.smsserver.transformer;


import com.ganeshban.smsserver.dto.ClientInfoDTO;
import com.ganeshban.smsserver.entity.ClientInfoEntity;
import com.ganeshban.smsserver.model.ClientInfoModel;
import org.mapstruct.Mapper;

import static com.ganeshban.smsserver.utils.Constants.Keyword.MAPPER_NAME;

@Mapper(componentModel = MAPPER_NAME)
public interface ClientInfoTransformer {

    ClientInfoEntity dtoToEntity(ClientInfoDTO dto);

    ClientInfoModel toModel(ClientInfoEntity entity);
}
