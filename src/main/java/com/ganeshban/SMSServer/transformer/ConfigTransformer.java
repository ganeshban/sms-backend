package com.ganeshban.smsserver.transformer;


import com.ganeshban.smsserver.dto.ConfigurationDTO;
import com.ganeshban.smsserver.entity.ConfigurationEntity;
import com.ganeshban.smsserver.model.ConfigurationModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.ganeshban.smsserver.utils.Constants.Keyword.MAPPER_NAME;

@Mapper(componentModel = MAPPER_NAME)
public interface ConfigTransformer {
    @Mapping(target = "createdAt", ignore = true)
    ConfigurationModel toModel(ConfigurationEntity entity);

    ConfigurationEntity toEntity(ConfigurationDTO dto);


}
