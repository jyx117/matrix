package com.cloud.matrix.biz;

import com.cloud.matrix.core.model.access.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cloud.matrix.core.service.access.UserService;

/**
 * @author yuxin.jyx(落玄)
 * @version $Id: HelloServiceImpl.java, v 0.1 2023-02-28 5:02 PM Michael Exp $$
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    private UserService userService;

    @Override
    public String sayHello() {
        return "say hello";
    }

    @Override
    public User getById(Long id) {
        return userService.getById(id);
    }
}
