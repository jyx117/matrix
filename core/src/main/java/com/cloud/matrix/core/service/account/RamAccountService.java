package com.cloud.matrix.core.service.account;

import com.cloud.matrix.core.model.account.RamAccount;
import java.util.List;

/**
 * @author michael
 * @version $Id: AccountConfigService.java, v 0.1 2023-03-16 5:31 PM Michael Exp $$
 */
public interface RamAccountService {

    long add(RamAccount request);

    RamAccount getByLoginName(String loginName, String providerUid);

    RamAccount getByName(String name, String providerUid);

    RamAccount getByUid(String uid, String providerUid);

    List<RamAccount> getByProviderUid(String providerUid);
}
