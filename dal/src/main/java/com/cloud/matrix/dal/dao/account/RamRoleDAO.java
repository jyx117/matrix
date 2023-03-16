package com.cloud.matrix.dal.dao.account;

import com.cloud.matrix.dal.model.account.RamRoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author michael
 * @version $ID: UserDAO.java, v0.1 2023-03-01 16:50 michael Exp
 */
@Mapper
public interface RamRoleDAO {

    long insert(RamRoleDO request);

    RamRoleDO selectByName(@Param("name") String name, @Param("providerUid") String providerUid,
                           @Param("tenant") String tenant);

    RamRoleDO selectByArn(@Param("arn") String arn, @Param("providerUid") String providerUid,
                          @Param("tenant") String tenant);

    List<RamRoleDO> selectByProviderUid(@Param("providerUid") String providerUid,
                                        @Param("tenant") String tenant);
}
