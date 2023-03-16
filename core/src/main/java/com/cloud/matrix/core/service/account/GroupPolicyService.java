package com.cloud.matrix.core.service.account;

import java.util.List;
import com.cloud.matrix.core.model.account.GroupPolicy;

/**
 * @author michael
 * @version $Id: AccountConfigService.java, v 0.1 2023-03-16 5:31 PM Michael Exp $$
 */
public interface GroupPolicyService {

    long add(GroupPolicy request);

    GroupPolicy getUnique(String groupName, String policyName, String providerUid);

    List<GroupPolicy> getByGroupName(String groupName, String providerUid);
}
