package com.cloud.matrix.core.service.access;

import com.cloud.matrix.core.model.access.User;
import com.cloud.matrix.core.model.access.UserAuth;

/**
 * @author michael
 * @version $ID: UserService.java, v0.1 2023-03-01 17:03 michael Exp
 */
public interface UserAuthService {

    public UserAuth getById(Long id);

    public User getByUidIdentity(String userId);

    public long add(User request);
}
