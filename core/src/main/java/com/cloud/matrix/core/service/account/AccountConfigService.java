package com.cloud.matrix.core.service.account;

import com.cloud.matrix.core.model.account.AccountConfig;
import java.util.List;

/**
 * @author michael
 * @version $Id: AccountConfigService.java, v 0.1 2023-03-16 5:31 PM Michael Exp $$
 */
public interface AccountConfigService {

    long add(AccountConfig request);

    AccountConfig getUnique(String accountUid, String providerUid, String k);

    List<AccountConfig> getByAccountUid(String accountUid, String providerUid);

    List<AccountConfig> getByUidListAndKeys(List uidList, List keys);

}
