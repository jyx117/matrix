package com.cloud.matrix.core.service.account;

import java.util.List;
import com.cloud.matrix.core.model.account.AccountPolicy;

/**
 * @author michael
 * @version $Id: AccountConfigService.java, v 0.1 2023-03-16 5:31 PM Michael Exp $$
 */
public interface AccountPolicyService {

    long add(AccountPolicy request);

    AccountPolicy getUnique(String accountUid, String policyName, String providerUid);

    List<AccountPolicy> getByAccountUid(String accountUid, String providerUid);
}
