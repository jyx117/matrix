package com.cloud.matrix.dal.dao;

import com.cloud.matrix.dal.model.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author michael
 * @version $ID: UserDAO.java, v0.1 2023-03-01 16:50 michael Exp
 */
@Repository
@Mapper
public interface UserDAO {

    UserDO selectById(@Param("id") Long id);
}
