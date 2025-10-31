package com.ganeshban.smsserver.transformer;

import com.ganeshban.smsserver.entity.RoleEntity;
import com.ganeshban.smsserver.entity.UserEntity;
import com.ganeshban.smsserver.entity.UserRoleEntity;
import com.ganeshban.smsserver.model.RoleModel;
import com.ganeshban.smsserver.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ganeshban.smsserver.utils.Constants.Keyword.ACTIVE;
import static com.ganeshban.smsserver.utils.Constants.Keyword.MAPPER_NAME;

@Mapper(componentModel = MAPPER_NAME)
public interface UserTransformer {

    @Mapping(target = "roles", expression = "java(mapUserRolesToRoles(entity.getRoles()))")
    @Mapping(target = "createdAt", ignore = true)
    UserModel toModel(UserEntity entity);

    default Set<RoleModel> mapUserRolesToRoles(Set<UserRoleEntity> userRoleEntities) {
        if (userRoleEntities == null) return Collections.emptySet();
        return userRoleEntities.stream()
                .filter(a -> a.getStatus().equalsIgnoreCase(ACTIVE))
                .map(UserRoleEntity::getRole)
                .map(this::mapRoleToModel)
                .collect(Collectors.toSet());
    }

    @Named("mapRoleToModel")
    RoleModel mapRoleToModel(RoleEntity roleEntity);
}
