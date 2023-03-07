package com.cloud.matrix.biz.access.controller;

import com.cloud.matrix.biz.access.service.UserBizService;
import com.cloud.matrix.common.result.BaseResult;
import com.cloud.matrix.core.CoreContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author michael
 * @version $Id: UserController.java, v 0.1 2023-03-07 10:57 AM Michael Exp $$
 */
@RestController
public class UserController {

    @Autowired
    private UserBizService userBizService;

    @PostMapping(value = "/gateway/access/getUser.json")
    public BaseResult getUser() {
        String userId = CoreContext.getUser();
        return BaseResult.success(userBizService.queryUserDetail(userId));
    }

}
