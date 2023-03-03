package com.cloud.matrix.start.gateway;

import com.alibaba.fastjson.JSON;
import com.cloud.matrix.biz.access.service.HelloService;
import com.cloud.matrix.common.enums.ErrorCode;
import com.cloud.matrix.common.exception.BizException;
import com.cloud.matrix.core.model.access.User;
import com.cloud.matrix.util.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michael
 * @version $Id: HelloController.java, v 0.1 2023-02-28 4:55 PM Michael Exp $$
 */
@RestController
@Slf4j
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    private HelloService        helloService;

    @RequestMapping("/hello1")
    public String hello1() {
        throw new RuntimeException("hello1 exc");
    }

    @RequestMapping("/hello11")
    public String hello11() {
        throw new BizException(ErrorCode.BIZ_COMMON_EXCEPTION);
    }

    @RequestMapping("/hello2")
    public String hello2() {
        LogUtil.info(logger, "[hello2][user=aaa]");
        return helloService.sayHello();
    }

    @RequestMapping("/hello3")
    public String hello3(Long id) {
        User user = helloService.getById(id);
        return null == user ? "null" : JSON.toJSONString(user);
    }
}
