package com.cloud.matrix.core.model.account;

import java.util.Date;
import lombok.Data;

/**
 * @author michael
 * @version $Id: RamAccountDO.java, v 0.1 2023-03-16 1:50 PM Michael Exp $$
 */
@Data
public class RamRole extends AccountBaseModel {

    /** 子账号uid */
    private String name;

    /** 登录名截取前一段 */
    private String arn;

    /** 角色登录超时时间 */
    private int    sessionTimeout;

    /** 备注 */
    private String comment;

    /** 账号创建时间 */
    private Date   createTime;
}
