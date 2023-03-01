package com.cloud.matrix.biz;

import com.cloud.matrix.core.model.access.User;

/**
 * @author yuxin.jyx(落玄)
 * @version $Id: HelloService.java, v 0.1 2023-02-28 5:02 PM Michael Exp $$
 */
public interface HelloService {

    public String sayHello();

    public User getById(Long id);
}
