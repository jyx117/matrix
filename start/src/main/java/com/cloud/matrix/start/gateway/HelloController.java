package com.cloud.matrix.start.gateway;

import com.alibaba.fastjson.JSON;
import com.cloud.matrix.biz.access.service.HelloService;
import com.cloud.matrix.core.model.access.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michael
 * @version $Id: HelloController.java, v 0.1 2023-02-28 4:55 PM Michael Exp $$
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello1")
    public String hello1() {
        return "hello world";
    }

    @RequestMapping("/hello2")
    public String hello2() {
        return helloService.sayHello();
    }

    @RequestMapping("/hello3")
    public String hello3(Long id) {
        User user = helloService.getById(id);
        return null == user ? "null" : JSON.toJSONString(user);
    }
}
