package com.cloud.matrix.core.service.access;

import com.cloud.matrix.core.model.access.User;

/**
 * @author michael
 * @version $ID: UserService.java, v0.1 2023-03-01 17:03 michael Exp
 */
public interface UserService {

    public User getById(Long id);
}
