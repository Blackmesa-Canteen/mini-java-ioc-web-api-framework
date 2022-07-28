package org.example.handler;

import io.swen90007sm2.framework.annotation.ioc.AutoInjected;
import io.swen90007sm2.framework.annotation.mvc.*;
import io.swen90007sm2.framework.bean.R;
import io.swen90007sm2.framework.common.constant.RequestMethod;
import org.example.blo.IUserBlo;
import org.example.entity.User;

import java.util.List;

@Handler
public class TestHandler {

    @AutoInjected
    private IUserBlo userService;

    @HandlesRequest(path = "/test", method = RequestMethod.GET)
    public R getOrder() {

        User user = userService.getUserById(145L);
        user.setName("测试用");
        return R.ok().setData(user);
    }

    @HandlesRequest(path = "/map", method = RequestMethod.POST)
    public R echoMap(@RequestJsonBody List<User> users) {

        return R.ok().setData(users);
    }

}
