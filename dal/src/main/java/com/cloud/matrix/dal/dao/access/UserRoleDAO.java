package com.cloud.matrix.dal.dao.access;

import com.cloud.matrix.dal.model.access.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cloud.matrix.dal.model.access.UserAuthDO;

import java.util.List;

/**
 * @author michael
 * @version $ID: UserDAO.java, v0.1 2023-03-01 16:50 michael Exp
 */
@Mapper
public interface UserRoleDAO {

    long insert(UserRoleDO request);

    UserRoleDO selectById(@Param("id") Long id);

    UserRoleDO selectByUnique(@Param("userId") String userId, @Param("roleCode") String roleCode,
                              @Param("tenant") String tenant);

    List<UserRoleDO> selectByUserId(@Param("userId") String userId);

}
