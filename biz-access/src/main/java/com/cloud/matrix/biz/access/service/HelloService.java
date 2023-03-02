package com.cloud.matrix.biz.access.service;

import com.cloud.matrix.core.model.access.User;

/**
 * @author michael
 * @version $Id: HelloService.java, v 0.1 2023-02-28 5:02 PM Michael Exp $$
 */
public interface HelloService {

    public String sayHello();

    public User getById(Long id);
}
