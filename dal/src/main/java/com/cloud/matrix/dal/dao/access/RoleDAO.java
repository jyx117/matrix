package com.cloud.matrix.dal.dao.access;

import com.cloud.matrix.dal.model.access.RoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author michael
 * @version $ID: UserDAO.java, v0.1 2023-03-01 16:50 michael Exp
 */
@Mapper
public interface RoleDAO {

    long insert(RoleDO request);

    RoleDO selectById(@Param("id") Long id);

    RoleDO selectByCode(@Param("code") String code);

}
