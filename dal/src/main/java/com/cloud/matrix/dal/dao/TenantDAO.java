package com.cloud.matrix.dal.dao;

import com.cloud.matrix.dal.model.TenantDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author michael
 * @version $Id: TenantDAO.java, v 0.1 2023-03-02 3:38 PM Michael Exp $$
 */
@Mapper
public interface TenantDAO {

    long insert(TenantDO request);

    TenantDO selectByCode(@Param("code") String code);
}
