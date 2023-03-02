package com.cloud.matrix.dal.dao.access;

import com.cloud.matrix.dal.model.access.UserAuthDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author michael
 * @version $ID: UserDAO.java, v0.1 2023-03-01 16:50 michael Exp
 */
@Mapper
public interface UserAuthDAO {

    long insert(UserAuthDO request);

    UserAuthDO selectById(@Param("id") Long id);

    UserAuthDO selectByUidIdentity(@Param("userId") String userId,
                                   @Param("identifier") String identifier,
                                   @Param("identityType") String identityType);

}
