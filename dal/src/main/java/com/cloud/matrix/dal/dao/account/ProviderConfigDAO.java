package com.cloud.matrix.dal.dao.account;

import java.util.List;
import com.cloud.matrix.dal.model.account.ProviderConfigDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author michael
 * @version $ID: UserDAO.java, v0.1 2023-03-01 16:50 michael Exp
 */
@Mapper
public interface ProviderConfigDAO {

    long insert(ProviderConfigDO request);

    ProviderConfigDO selectUnique(@Param("providerUid") String providerUid, @Param("k") String k,
                                  @Param("tenant") String tenant);

    List<ProviderConfigDO> selectByProviderUid(@Param("providerUid") String providerUid,
                                               @Param("tenant") String tenant);

}
