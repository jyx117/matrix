package com.cloud.matrix.core.service.account;

import java.util.List;
import com.cloud.matrix.core.model.account.ProviderConfig;

/**
 * @author michael
 * @version $Id: AccountConfigService.java, v 0.1 2023-03-16 5:31 PM Michael Exp $$
 */
public interface ProviderConfigService {

    long add(ProviderConfig request);

    ProviderConfig getUnique(String providerUid, String k);

    List<ProviderConfig> getByProviderUid(String providerUid);
}
