package com.cloud.matrix.core.model;

import com.cloud.matrix.core.enums.PermissionType;
import com.cloud.matrix.core.enums.UserIdentityType;
import com.cloud.matrix.core.model.access.*;
import com.cloud.matrix.dal.model.access.*;
import com.cloud.matrix.dal.model.TenantDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.util.StringUtils;

/**
 * @author michael
 * @version $ID: Convertor.java, v0.1 2023-03-01 16:56 michael Exp
 */
@Mapper
public interface Convertor {

    Convertor INSTANCE = Mappers.getMapper(Convertor.class);

    Tenant convert2Model(TenantDO request);

    TenantDO convert2Do(Tenant request);

    User convert2Model(UserDO userDO);

    UserDO convert2Do(User user);

    @Mapping(source = "identityType", target = "identityType")
    UserAuth convert2Model(UserAuthDO request);

    @Mapping(source = "identityType", target = "identityType")
    UserAuthDO convert2Do(UserAuth request);

    default UserIdentityType convertUserIdentityType(String identityType) {
        for (UserIdentityType item : UserIdentityType.values()) {
            if (item.name().equals(identityType)) {
                return item;
            }
        }
        return null;
    }

    Role convert2Model(RoleDO request);

    RoleDO convert2Do(Role request);

    @Mapping(source = "type", target = "type")
    Permission convert2Model(PermissionDO request);

    @Mapping(source = "type", target = "type")
    PermissionDO convert2Do(Permission request);

    default PermissionType convertPermissionType(String type) {
        for (PermissionType item : PermissionType.values()) {
            if (item.name().equals(type)) {
                return item;
            }
        }
        return null;
    }

    UserRole convert2Model(UserRoleDO request);

    UserRoleDO convert2Do(UserRole request);

    RolePermission convert2Model(RolePermissionDO request);

    RolePermissionDO convert2Do(RolePermission request);
}
