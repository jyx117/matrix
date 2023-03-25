package com.cloud.matrix.service.model.account;

import com.cloud.matrix.service.enums.account.CloudSourceType;
import com.cloud.matrix.service.model.TenantBase;
import lombok.Data;

/**
 * @author michael
 * @version $Id: Provider.java, v 0.1 2023-03-16 1:42 PM Michael Exp $$
 */
@Data
public class ProviderDetail extends TenantBase {

    private String          uid;

    private String          name;

    private CloudSourceType cloudSource;

}
