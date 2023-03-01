package com.cloud.matrix.core.model;

import com.cloud.matrix.core.model.access.User;
import com.cloud.matrix.dal.model.UserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author michael
 * @version $ID: Convertor.java, v0.1 2023-03-01 16:56 michael Exp
 */
@Mapper
public abstract class Convertor {

    public static final Convertor INSTANCE = Mappers.getMapper(Convertor.class);

    public abstract User convert2Do(UserDO userDO);

    public abstract UserDO convert2Do(User user);
}
