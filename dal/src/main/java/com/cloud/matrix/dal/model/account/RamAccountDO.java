package com.cloud.matrix.dal.model.account;

import lombok.Data;
import java.util.Date;

/**
 * @author michael
 * @version $Id: RamAccountDO.java, v 0.1 2023-03-16 1:50 PM Michael Exp $$
 */
@Data
public class RamAccountDO extends AccountBaseDO {

    /** 子账号uid */
    private String uid;

    /** 登录名截取前一段 */
    private String name;

    /** 登录名，唯一键 */
    private String loginName;

    /** 显示名 */
    private String showName;

    /** 备注 */
    private String comment;

    /** 账号创建时间 */
    private Date createTime;

    /** 电话 */
    private String phone;

    /** 邮件 */
    private String mail;
}
