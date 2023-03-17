package com.cloud.matrix.core.service.account;

import java.util.List;

import com.cloud.matrix.core.model.account.AccountConfig;
import com.cloud.matrix.core.model.account.AccountUser;

/**
 * @author michael
 * @version $Id: AccountConfigService.java, v 0.1 2023-03-16 5:31 PM Michael Exp $$
 */
public interface AccountUserService {

    long add(AccountUser request);

    AccountUser getUnique(String userId, String accountUid, String providerUid);

    List<AccountUser> getByUserIdAndProviderUid(String userId, String providerUid);
}
