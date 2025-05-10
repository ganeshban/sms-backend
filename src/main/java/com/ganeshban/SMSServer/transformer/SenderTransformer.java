package com.ganeshban.smsserver.transformer;


import com.ganeshban.smsserver.entity.SenderEntity;
import com.ganeshban.smsserver.model.SenderModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SenderTransformer {
    SenderModel toModel(SenderEntity entity);


}
