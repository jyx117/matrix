package com.cloud.matrix.core.service.account;

import com.cloud.matrix.core.model.account.Provider;

/**
 * @author michael
 * @version $Id: AccountConfigService.java, v 0.1 2023-03-16 5:31 PM Michael Exp $$
 */
public interface ProviderService {

    long add(Provider request);

    Provider getUnique(String uid);
}
