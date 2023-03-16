package com.cloud.matrix.dal.dao.account;

import java.util.List;
import com.cloud.matrix.dal.model.account.AccountPolicyDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author michael
 * @version $ID: UserDAO.java, v0.1 2023-03-01 16:50 michael Exp
 */
@Mapper
public interface AccountPolicyDAO {

    long insert(AccountPolicyDO request);

    AccountPolicyDO selectUnique(@Param("accountUid") String accountUid,
                                 @Param("policyName") String policyName,
                                 @Param("providerUid") String providerUid,
                                 @Param("tenant") String tenant);

    List<AccountPolicyDO> selectByAccountUid(@Param("accountUid") String accountUid,
                                      @Param("providerUid") String providerUid,
                                      @Param("tenant") String tenant);
}
