package com.cloud.matrix.dal.dao.account;

import com.cloud.matrix.dal.model.account.AccountGroupDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author michael
 * @version $ID: UserDAO.java, v0.1 2023-03-01 16:50 michael Exp
 */
@Mapper
public interface AccountGroupDAO {

    long insert(AccountGroupDO request);

    AccountGroupDO selectUnique(@Param("accountUid") String accountUid,
                            @Param("groupName") String groupName,
                            @Param("providerUid") String providerUid,
                            @Param("tenant") String tenant);

    List<AccountGroupDO> selectByAccountUid(@Param("accountUid") String accountUid,
                                        @Param("providerUid") String providerUid,
                                        @Param("tenant") String tenant);
}
