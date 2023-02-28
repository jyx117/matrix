package com.cloud.matrix.biz;

import org.springframework.stereotype.Service;

/**
 * @author yuxin.jyx(落玄)
 * @version $Id: HelloServiceImpl.java, v 0.1 2023-02-28 5:02 PM Michael Exp $$
 */
@Service
public class HelloServiceImpl implements HelloService{
    @Override
    public String sayHello() {
        return "say hello";
    }
}
