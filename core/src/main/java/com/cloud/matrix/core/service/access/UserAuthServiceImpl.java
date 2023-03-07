package com.cloud.matrix.core.service.access;

import com.cloud.matrix.core.model.access.UserAuth;
import com.cloud.matrix.dal.dao.access.UserAuthDAO;
import org.springframework.stereotype.Service;
import com.cloud.matrix.core.model.Convertor;
import javax.annotation.Resource;

/**
 * @author michael
 * @version $ID: UserServiceImpl.java, v0.1 2023-03-01 17:03 michael Exp
 */
@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Resource
    private UserAuthDAO userAuthDAO;

    @Override
    public UserAuth getById(Long id) {
        return Convertor.INSTANCE.convert2Model(userAuthDAO.selectById(id));
    }

    @Override
    public UserAuth getByUserId(String userId) {
        return Convertor.INSTANCE.convert2Model(userAuthDAO.selectByUserId(userId));
    }

    @Override
    public UserAuth getByIdentity(String identifier, String identityType) {
        return Convertor.INSTANCE
            .convert2Model(userAuthDAO.selectByIdentity(identifier, identityType));
    }

    @Override
    public long add(UserAuth request) {
        if (null == request) {
            return -1;
        }
        return userAuthDAO.insert(Convertor.INSTANCE.convert2Do(request));
    }
}
