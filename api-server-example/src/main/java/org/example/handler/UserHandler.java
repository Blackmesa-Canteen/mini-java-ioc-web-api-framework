package org.example.handler;

import io.swen90007sm2.framework.annotation.ioc.AutoInjected;
import io.swen90007sm2.framework.annotation.mvc.Handler;
import io.swen90007sm2.framework.annotation.mvc.HandlesRequest;
import io.swen90007sm2.framework.bean.R;
import io.swen90007sm2.framework.bean.RequestSessionBean;
import io.swen90007sm2.framework.common.constant.RequestMethod;
import org.example.entity.User;
import org.example.blo.IUserBlo;

@Handler(path = "/admin")
public class UserHandler {

    @AutoInjected
    private IUserBlo userService;

    @HandlesRequest(path = "/user", method = RequestMethod.GET)
    public R getUser(RequestSessionBean param) {
        Long id = Long.parseLong((String) param.getRequestParamMap().get("id"));
        User user = userService.getUserById(id);

        return R.ok().setData(user);
    }

    @HandlesRequest(path = "/user", method = RequestMethod.POST)
    public R addUser(RequestSessionBean param) {
        // TODO 轉型問題
//        Long id = Long.parseLong((String) param.getRequestParamMap().get("id"));
        Long id = 233L;
        String name = (String) param.getRequestParamMap().get("name");
        int age = (int) param.getRequestParamMap().get("age");

        User user = new User(id, name, age);

        return R.ok().setData(user);
    }

}
