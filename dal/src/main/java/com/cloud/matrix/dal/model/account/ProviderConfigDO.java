package com.cloud.matrix.dal.model.account;

import lombok.Data;

/**
 * @author michael
 * @version $Id: RamAccountDO.java, v 0.1 2023-03-16 1:50 PM Michael Exp $$
 */
@Data
public class ProviderConfigDO extends AccountBaseDO {

    /** 配置key */
    private String k;

    /** 配置value */
    private String v;

    /** 风险等级 */
    private int    level;

    /** 描述信息 */
    private String description;
}
