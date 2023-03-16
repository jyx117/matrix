package com.cloud.matrix.dal.dao.account;

import com.cloud.matrix.dal.model.account.RamAccountDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author michael
 * @version $ID: UserDAO.java, v0.1 2023-03-01 16:50 michael Exp
 */
@Mapper
public interface RamAccountDAO {

    long insert(RamAccountDO request);

    RamAccountDO selectByLoginName(@Param("loginName") String loginName,
                                   @Param("providerUid") String providerUid,
                                   @Param("tenant") String tenant);

    RamAccountDO selectByName(@Param("name") String name, @Param("providerUid") String providerUid,
                              @Param("tenant") String tenant);

    RamAccountDO selectByUid(@Param("uid") String uid, @Param("providerUid") String providerUid,
                             @Param("tenant") String tenant);

    List<RamAccountDO> selectByProviderUid(@Param("providerUid") String providerUid,
                                           @Param("tenant") String tenant);

}
