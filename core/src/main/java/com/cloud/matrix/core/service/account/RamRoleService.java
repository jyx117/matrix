package com.cloud.matrix.core.service.account;

import java.util.List;

import com.cloud.matrix.core.model.account.RamGroup;
import com.cloud.matrix.core.model.account.RamRole;
import com.cloud.matrix.dal.dao.account.RamRoleDAO;

/**
 * @author michael
 * @version $Id: AccountConfigService.java, v 0.1 2023-03-16 5:31 PM Michael Exp $$
 */
public interface RamRoleService {

    long add(RamRole request);

    RamRole getByName(String name, String providerUid);

    RamRole getByArn(String arn, String providerUid);

    List<RamRole> getByProviderUid(String providerUid);
}
