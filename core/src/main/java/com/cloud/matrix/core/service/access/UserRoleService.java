package com.cloud.matrix.core.service.access;

import com.cloud.matrix.core.model.access.UserRole;
import java.util.List;

/**
 * @author michael
 * @version $ID: UserService.java, v0.1 2023-03-01 17:03 michael Exp
 */
public interface UserRoleService {

    public long add(UserRole request);

    public UserRole getById(Long id);

    public UserRole getByUnique(String userId, String roleCode, String tenant);

    public List<UserRole> getByUserId(String userId);

}
