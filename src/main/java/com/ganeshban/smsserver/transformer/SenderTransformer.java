package com.ganeshban.smsserver.transformer;


import com.ganeshban.smsserver.entity.SenderEntity;
import com.ganeshban.smsserver.model.SenderModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.ganeshban.smsserver.utils.Constants.Keyword.MAPPER_NAME;

@Mapper(componentModel = MAPPER_NAME)
public interface SenderTransformer {
    @Mapping(target = "createdAt", ignore = true)
    SenderModel toModel(SenderEntity entity);


}
