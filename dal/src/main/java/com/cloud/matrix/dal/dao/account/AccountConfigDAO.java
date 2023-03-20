package com.cloud.matrix.dal.dao.account;

import com.cloud.matrix.dal.model.account.AccountConfigDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author michael
 * @version $ID: UserDAO.java, v0.1 2023-03-01 16:50 michael Exp
 */
@Mapper
public interface AccountConfigDAO {

    long insert(AccountConfigDO request);

    AccountConfigDO selectUnique(@Param("accountUid") String accountUid,
                                 @Param("providerUid") String providerUid, @Param("k") String k,
                                 @Param("tenant") String tenant);

    List<AccountConfigDO> selectByAccountUid(@Param("accountUid") String accountUid,
                                             @Param("providerUid") String providerUid,
                                             @Param("tenant") String tenant);

    List<AccountConfigDO> selectByUidListAndKeys(@Param("uidList") List uidList,
                                                 @Param("keys") List keys,
                                                 @Param("tenant") String tenant);

}
