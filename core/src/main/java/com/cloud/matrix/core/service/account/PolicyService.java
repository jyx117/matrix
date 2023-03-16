package com.cloud.matrix.core.service.account;

import java.util.List;
import com.cloud.matrix.core.model.account.Policy;

/**
 * @author michael
 * @version $Id: AccountConfigService.java, v 0.1 2023-03-16 5:31 PM Michael Exp $$
 */
public interface PolicyService {

    long add(Policy request);

    Policy getByName(String name, String providerUid);

    List<Policy> getByProviderUid(String providerUid);
}
