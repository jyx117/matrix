package com.cloud.matrix.dal.dao.access;

import com.cloud.matrix.dal.model.access.PermissionDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author michael
 * @version $ID: UserDAO.java, v0.1 2023-03-01 16:50 michael Exp
 */
@Mapper
public interface PermissionDAO {

    long insert(PermissionDO request);

    PermissionDO selectById(@Param("id") Long id);

    PermissionDO selectByCodeAndType(@Param("code") String code, @Param("type") String type);

}
