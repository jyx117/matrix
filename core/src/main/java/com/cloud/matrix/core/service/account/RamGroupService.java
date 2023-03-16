package com.cloud.matrix.core.service.account;

import java.util.List;
import com.cloud.matrix.core.model.account.RamGroup;

/**
 * @author michael
 * @version $Id: AccountConfigService.java, v 0.1 2023-03-16 5:31 PM Michael Exp $$
 */
public interface RamGroupService {

    long add(RamGroup request);

    RamGroup getByName(String name, String providerUid);

    List<RamGroup> getByProviderUid(String providerUid);
}
