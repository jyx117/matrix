package com.cloud.matrix.core.model;

import com.cloud.matrix.core.enums.CloudSourceType;
import com.cloud.matrix.core.enums.PermissionType;
import com.cloud.matrix.core.enums.UserIdentityType;
import com.cloud.matrix.core.model.access.*;
import com.cloud.matrix.core.model.account.*;
import com.cloud.matrix.dal.model.access.*;
import com.cloud.matrix.dal.model.account.*;
import com.cloud.matrix.dal.model.TenantDO;
import com.cloud.matrix.util.StringUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

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
            if (StringUtil.equalsIgnoreCase(item.name(), identityType)) {
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
            if (StringUtil.equalsIgnoreCase(item.name(), type)) {
                return item;
            }
        }
        return null;
    }

    UserRole convert2Model(UserRoleDO request);

    UserRoleDO convert2Do(UserRole request);

    RolePermission convert2Model(RolePermissionDO request);

    RolePermissionDO convert2Do(RolePermission request);

    @Mapping(source = "cloudSource", target = "cloudSource")
    Provider convert2Model(ProviderDO request);

    @Mapping(source = "cloudSource", target = "cloudSource")
    ProviderDO convert2Do(Provider request);

    default CloudSourceType convertCloudSourceType(String type) {
        for (CloudSourceType item : CloudSourceType.values()) {
            if (StringUtil.equalsIgnoreCase(item.name(), type)) {
                return item;
            }
        }
        return null;
    }

    RamAccount convert2Model(RamAccountDO request);

    RamAccountDO convert2Do(RamAccount request);

    AccountConfig convert2Model(AccountConfigDO request);

    AccountConfigDO convert2Do(AccountConfig request);

    RamGroup convert2Model(RamGroupDO request);

    RamGroupDO convert2Do(RamGroup request);

    AccountGroup convert2Model(AccountGroupDO request);

    AccountGroupDO convert2Do(AccountGroup request);

    RamRole convert2Model(RamRoleDO request);

    RamRoleDO convert2Do(RamRole request);

    Policy convert2Model(PolicyDO request);

    PolicyDO convert2Do(Policy request);

    AccountPolicy convert2Model(AccountPolicyDO request);

    AccountPolicyDO convert2Do(AccountPolicy request);

    GroupPolicy convert2Model(GroupPolicyDO request);

    GroupPolicyDO convert2Do(GroupPolicy request);

    RolePolicy convert2Model(RolePolicyDO request);

    RolePolicyDO convert2Do(RolePolicy request);
}
