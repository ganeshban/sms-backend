package com.ganeshban.smsserver.transformer;

import com.ganeshban.smsserver.entity.RoleEntity;
import com.ganeshban.smsserver.entity.UserEntity;
import com.ganeshban.smsserver.entity.UserRoleEntity;
import com.ganeshban.smsserver.model.RoleModel;
import com.ganeshban.smsserver.model.UserModel;
import com.ganeshban.smsserver.utils.Constants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = RoleTransformer.class)
public interface UserTransformer {

    @Mapping(target = "userRoles", expression = "java(mapUserRolesToRoles(entity.getUserRoles()))")
    @Mapping(source = "clientCode.clientCode",target = "clientCode")
    UserModel toModel(UserEntity entity);

    default Set mapUserRolesToRoles(Set<UserRoleEntity> userRoleEntities) {
        if (userRoleEntities == null) return Collections.emptySet();
        return userRoleEntities.stream()
                .filter(a -> a.getStatus().equalsIgnoreCase(Constants.ACTIVE))
                .map(UserRoleEntity::getRole)
                .map(this::mapRoleToModel)
                .collect(Collectors.toSet());
    }

    @Named("mapRoleToModel")
    RoleModel mapRoleToModel(RoleEntity roleEntity);
}
