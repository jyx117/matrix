package com.cloud.matrix.dal.dao.account;

import java.util.List;
import com.cloud.matrix.dal.model.account.RolePolicyDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author michael
 * @version $ID: UserDAO.java, v0.1 2023-03-01 16:50 michael Exp
 */
@Mapper
public interface RolePolicyDAO {

    long insert(RolePolicyDO request);

    RolePolicyDO selectUnique(@Param("roleName") String roleName,
                              @Param("policyName") String policyName,
                              @Param("providerUid") String providerUid,
                              @Param("tenant") String tenant);

    List<RolePolicyDO> selectByRoleName(@Param("roleName") String roleName,
                                        @Param("providerUid") String providerUid,
                                        @Param("tenant") String tenant);
}
