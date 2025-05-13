package com.ganeshban.smsserver.transformer;


import com.ganeshban.smsserver.entity.SenderEntity;
import com.ganeshban.smsserver.model.SenderModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SenderTransformer {
    @Mapping(target = "createdAt", ignore = true)
    SenderModel toModel(SenderEntity entity);


}
