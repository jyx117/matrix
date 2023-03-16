package com.cloud.matrix.core.service.account;

import java.util.List;
import com.cloud.matrix.core.model.account.AccountGroup;

/**
 * @author michael
 * @version $Id: AccountConfigService.java, v 0.1 2023-03-16 5:31 PM Michael Exp $$
 */
public interface AccountGroupService {

    long add(AccountGroup request);

    AccountGroup getUnique(String accountUid, String providerUid, String groupName);

    List<AccountGroup> getByAccountUid(String accountUid, String providerUid);
}
