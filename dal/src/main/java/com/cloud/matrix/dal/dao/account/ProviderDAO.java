package com.cloud.matrix.dal.dao.account;

import com.cloud.matrix.dal.model.account.ProviderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author michael
 * @version $ID: UserDAO.java, v0.1 2023-03-01 16:50 michael Exp
 */
@Mapper
public interface ProviderDAO {

    long insert(ProviderDO request);

    ProviderDO selectUnique(@Param("uid") String uid, @Param("cloudSource") String cloudSource,
                            @Param("tenant") String tenant);

}
