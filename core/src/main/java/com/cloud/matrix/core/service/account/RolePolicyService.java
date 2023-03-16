package com.cloud.matrix.core.service.account;

import java.util.List;
import com.cloud.matrix.core.model.account.RolePolicy;

/**
 * @author michael
 * @version $Id: AccountConfigService.java, v 0.1 2023-03-16 5:31 PM Michael Exp $$
 */
public interface RolePolicyService {

    long add(RolePolicy request);

    RolePolicy getUnique(String roleName, String policyName, String providerUid);

    List<RolePolicy> getByRoleName(String roleName, String providerUid);
}
