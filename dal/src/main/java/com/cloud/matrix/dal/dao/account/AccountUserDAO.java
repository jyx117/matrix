package com.cloud.matrix.dal.dao.account;

import java.util.List;
import com.cloud.matrix.dal.model.account.AccountUserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author michael
 * @version $ID: UserDAO.java, v0.1 2023-03-01 16:50 michael Exp
 */
@Mapper
public interface AccountUserDAO {

    long insert(AccountUserDO request);

    AccountUserDO selectUnique(@Param("userId") String userId,
                               @Param("accountUid") String accountUid,
                               @Param("providerUid") String providerUid,
                               @Param("tenant") String tenant);

    List<AccountUserDO> selectByUserIdAndProviderUid(@Param("userId") String userId,
                                                     @Param("providerUid") String providerUid,
                                                     @Param("tenant") String tenant);

}
