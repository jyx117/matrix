package com.cloud.matrix.dal.dao.access;

import com.cloud.matrix.dal.model.access.RolePermissionDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cloud.matrix.dal.model.access.PermissionDO;

import java.util.List;

/**
 * @author michael
 * @version $ID: UserDAO.java, v0.1 2023-03-01 16:50 michael Exp
 */
@Mapper
public interface RolePermissionDAO {

    long insert(RolePermissionDO request);

    RolePermissionDO selectById(@Param("id") Long id);

    RolePermissionDO selectUnique(@Param("roleCode") String roleCode,
                                  @Param("permissionCode") String permissionCode,
                                  @Param("permissionType") String permissionType,
                                  @Param("tenant") String tenant);

    List<RolePermissionDO> selectByRoleCode(@Param("roleCode") String roleCode,
                                            @Param("tenant") String tenant);
}
