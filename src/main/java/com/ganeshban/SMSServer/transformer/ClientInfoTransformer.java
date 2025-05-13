package com.ganeshban.smsserver.transformer;


import com.ganeshban.smsserver.DTO.ClientInfoDTO;
import com.ganeshban.smsserver.entity.ClientInfoEntity;
import com.ganeshban.smsserver.model.ClientInfoModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {SenderTransformer.class, UserTransformer.class})
public interface ClientInfoTransformer {

    ClientInfoEntity dtoToEntity(ClientInfoDTO dto);
    ClientInfoModel toModel (ClientInfoEntity entity);
}
