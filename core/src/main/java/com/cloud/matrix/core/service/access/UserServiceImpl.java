package com.cloud.matrix.core.service.access;

import com.cloud.matrix.core.model.Convertor;
import com.cloud.matrix.core.model.access.User;
import com.cloud.matrix.dal.dao.UserDAO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author michael
 * @version $ID: UserServiceImpl.java, v0.1 2023-03-01 17:03 michael Exp
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDAO userDAO;

    @Override
    public User getById(Long id) {
        return Convertor.INSTANCE.convert2Do(userDAO.selectById(id));
    }
}
