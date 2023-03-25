package com.cloud.matrix.web.controller.access;

import com.cloud.matrix.biz.service.access.UserBizService;
import com.cloud.matrix.core.CoreContext;
import com.cloud.matrix.service.result.DataResult;
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
    public DataResult getUser() {
        String userId = CoreContext.getUser();
        return DataResult.success(userBizService.queryUserDetail(userId));
    }

}
