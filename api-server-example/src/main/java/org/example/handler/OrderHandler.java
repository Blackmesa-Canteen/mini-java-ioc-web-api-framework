package org.example.handler;

import io.swen90007sm2.framework.annotation.ioc.AutoInjected;
import io.swen90007sm2.framework.annotation.mvc.*;
import io.swen90007sm2.framework.bean.R;
import io.swen90007sm2.framework.common.constant.RequestMethod;
import org.example.blo.IUserBlo;
import org.example.entity.User;

@Handler(path = "/order")
public class OrderHandler {

    @AutoInjected
    private IUserBlo userService;

    @HandlesRequest(path = "/{id}", method = RequestMethod.GET)
    public R getOrder(@PathVariable("id") Long id) {

        User user = userService.getUserById(id);
        user.setName("order的！！！");
        return R.ok().setData(user);
    }

}
