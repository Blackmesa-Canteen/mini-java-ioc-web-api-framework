package org.example.handler;

import io.swen90007sm2.framework.annotation.*;
import io.swen90007sm2.framework.bean.R;
import io.swen90007sm2.framework.bean.RequestParam;
import io.swen90007sm2.framework.constant.RequestMethod;
import org.example.entity.User;
import org.example.service.IUserService;

@Handler(path = "/user")
public class UserHandler {

    @AutoInjected
    private IUserService userService;

    @HandlesRequest(path = "/get", method = RequestMethod.GET)
    public R getUser(RequestParam param) {
        Long id = Long.parseLong((String) param.getRequestParamMap().get("id"));
        User user = userService.getUserById(id);

        return R.ok().setData(user);
    }

    @HandlesRequest(path = "/insert", method = RequestMethod.POST)
    public R addUser(RequestParam param) {
        // TODO 轉型問題
//        Long id = Long.parseLong((String) param.getRequestParamMap().get("id"));
        Long id = 233L;
        String name = (String) param.getRequestParamMap().get("name");
        int age = (int) param.getRequestParamMap().get("age");

        User user = new User(id, name, age);

        return R.ok().setData(user);
    }

}
