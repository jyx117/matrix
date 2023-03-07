package com.cloud.matrix.core.service.access;

import com.cloud.matrix.core.model.access.UserRole;
import com.cloud.matrix.dal.dao.access.UserRoleDAO;
import com.cloud.matrix.dal.model.access.UserRoleDO;
import org.springframework.stereotype.Service;

import com.cloud.matrix.core.model.Convertor;
import com.cloud.matrix.core.model.access.UserAuth;
import com.cloud.matrix.dal.dao.access.UserAuthDAO;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author michael
 * @version $ID: UserServiceImpl.java, v0.1 2023-03-01 17:03 michael Exp
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserRoleDAO userRoleDAO;

    @Override
    public UserRole getById(Long id) {
        return Convertor.INSTANCE.convert2Model(userRoleDAO.selectById(id));
    }

    @Override
    public UserRole getByUnique(String userId, String roleCode, String tenant) {
        return Convertor.INSTANCE
            .convert2Model(userRoleDAO.selectByUnique(userId, roleCode, tenant));
    }

    @Override
    public List<UserRole> getByUserId(String userId) {
        List<UserRoleDO> userRoleDOS = userRoleDAO.selectByUserId(userId);
        return userRoleDOS == null || userRoleDOS.size() < 1 ? null
            : userRoleDOS.stream().map(item -> {
                return Convertor.INSTANCE.convert2Model(item);
            }).collect(Collectors.toList());
    }

    @Override
    public long add(UserRole request) {
        if (null == request) {
            return -1;
        }
        return userRoleDAO.insert(Convertor.INSTANCE.convert2Do(request));
    }
}
