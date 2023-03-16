package com.cloud.matrix.core.model.account;

import java.util.Date;
import lombok.Data;

/**
 * @author michael
 * @version $Id: RamAccountDO.java, v 0.1 2023-03-16 1:50 PM Michael Exp $$
 */
@Data
public class RamGroup extends AccountBaseModel {

    /** 用户组名 */
    private String name;

    /** 显示名 */
    private String showName;

    /** 备注 */
    private String comment;

    /** 账号创建时间 */
    private Date createTime;
    
}
