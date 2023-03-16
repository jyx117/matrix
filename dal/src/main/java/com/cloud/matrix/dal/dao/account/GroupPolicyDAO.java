package com.cloud.matrix.dal.dao.account;

import java.util.List;
import com.cloud.matrix.dal.model.account.GroupPolicyDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author michael
 * @version $ID: UserDAO.java, v0.1 2023-03-01 16:50 michael Exp
 */
@Mapper
public interface GroupPolicyDAO {

    long insert(GroupPolicyDO request);

    GroupPolicyDO selectUnique(@Param("groupName") String groupName,
                               @Param("policyName") String policyName,
                               @Param("providerUid") String providerUid,
                               @Param("tenant") String tenant);

    List<GroupPolicyDO> selectByGroupName(@Param("groupName") String groupName,
                                          @Param("providerUid") String providerUid,
                                          @Param("tenant") String tenant);
}
