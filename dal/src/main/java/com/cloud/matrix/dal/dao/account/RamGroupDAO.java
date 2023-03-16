package com.cloud.matrix.dal.dao.account;

import com.cloud.matrix.dal.model.account.RamGroupDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author michael
 * @version $ID: UserDAO.java, v0.1 2023-03-01 16:50 michael Exp
 */
@Mapper
public interface RamGroupDAO {

    long insert(RamGroupDO request);

    RamGroupDO selectByName(@Param("name") String name, @Param("providerUid") String providerUid,
                            @Param("tenant") String tenant);

    List<RamGroupDO> selectByProviderUid(@Param("providerUid") String providerUid,
                                         @Param("tenant") String tenant);

}
