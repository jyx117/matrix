package com.cloud.matrix.core.model.account;

import lombok.Data;

/**
 * @author michael
 * @version $Id: RamAccountDO.java, v 0.1 2023-03-16 1:50 PM Michael Exp $$
 */
@Data
public class Policy extends AccountBaseModel {

    /** 权限名 */
    private String name;

    /** 类型 */
    private String type;

    /** 备注 */
    private String comment;

    /** 权限文本 */
    private String document;
}
