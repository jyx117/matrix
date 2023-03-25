package com.cloud.matrix.biz.service.account;

import com.cloud.matrix.core.model.account.*;
import com.cloud.matrix.service.enums.account.AccountUserType;
import com.cloud.matrix.service.enums.account.CloudSourceType;
import com.cloud.matrix.service.model.account.*;
import com.cloud.matrix.util.StringUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author michael
 * @version $ID: AccountConvertor.java, v0.1 2023-03-24 19:41 michael Exp
 */
@Mapper
public interface AccountConvertor {

    AccountConvertor INSTANCE = Mappers.getMapper(AccountConvertor.class);

    Account convert2Biz(RamAccount request);

    RamAccount convert2Core(Account request);

    AccountConfigDetail convert2Biz(AccountConfig request);

    AccountConfig convert2Core(AccountConfigDetail request);

    AccountGroupMapping convert2Biz(AccountGroup request);

    AccountGroup convert2Core(AccountGroupMapping request);

    @Mapping(source = "type", target = "type")
    AccountUserMapping convert2Biz(AccountUser request);

    @Mapping(source = "type", target = "type")
    AccountUser convert2Core(AccountUserMapping request);

    default AccountUserType convertAccountUserType(String type) {
        for (AccountUserType item : AccountUserType.values()) {
            if (StringUtil.equalsIgnoreCase(item.name(), type)) {
                return item;
            }
        }
        return null;
    }

    GroupPolicyMapping convert2Biz(GroupPolicy request);

    GroupPolicy convert2Core(GroupPolicyMapping request);

    PolicyDetail convert2Biz(Policy request);

    Policy convert2Core(PolicyDetail request);

    ProviderConfigDetail convert2Biz(ProviderConfig request);

    ProviderConfig convert2Core(ProviderConfigDetail request);

    @Mapping(source = "cloudSource", target = "cloudSource")
    ProviderDetail convert2Biz(Provider request);

    @Mapping(source = "cloudSource", target = "cloudSource")
    Provider convert2Core(ProviderDetail request);

    default CloudSourceType convertCloudSourceType(String cloudSource) {
        for (CloudSourceType item : CloudSourceType.values()) {
            if (StringUtil.equalsIgnoreCase(item.name(), cloudSource)) {
                return item;
            }
        }
        return null;
    }

    RamGroupDetail convert2Biz(RamGroup request);

    RamGroup convert2Core(RamGroupDetail request);

    RamRoleDetail convert2Biz(RamRole request);

    RamRole convert2Core(RamRoleDetail request);

    RolePolicyMapping convert2Biz(RolePolicy request);

    RolePolicy convert2Core(RolePolicyMapping request);
}
