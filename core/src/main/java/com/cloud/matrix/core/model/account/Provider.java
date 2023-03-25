package com.cloud.matrix.core.model.account;

import com.cloud.matrix.core.TenantBaseModel;
import lombok.Data;

/**
 * @author michael
 * @version $Id: Provider.java, v 0.1 2023-03-16 1:42 PM Michael Exp $$
 */
@Data
public class Provider extends TenantBaseModel {

    private String uid;

    private String name;

    private String cloudSource;

}
