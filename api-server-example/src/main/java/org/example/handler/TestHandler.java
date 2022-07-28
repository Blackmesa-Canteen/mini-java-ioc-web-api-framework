package org.example.handler;

import io.swen90007sm2.framework.annotation.ioc.AutoInjected;
import io.swen90007sm2.framework.annotation.mvc.Handler;
import io.swen90007sm2.framework.annotation.mvc.HandlesRequest;
import io.swen90007sm2.framework.annotation.mvc.PathVariable;
import io.swen90007sm2.framework.bean.R;
import io.swen90007sm2.framework.common.constant.RequestMethod;
import org.example.blo.IUserBlo;
import org.example.entity.User;

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

}
