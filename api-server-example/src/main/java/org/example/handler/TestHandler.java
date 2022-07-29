package org.example.handler;

import io.swen90007sm2.framework.annotation.ioc.AutoInjected;
import io.swen90007sm2.framework.annotation.mvc.*;
import io.swen90007sm2.framework.annotation.validation.Validated;
import io.swen90007sm2.framework.bean.R;
import io.swen90007sm2.framework.common.constant.RequestMethod;
import org.example.blo.IUserBlo;
import org.example.entity.User;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import java.util.List;

@Handler
@Validated
public class TestHandler {

    @AutoInjected
    private IUserBlo userService;

    @HandlesRequest(path = "/test/{id}", method = RequestMethod.GET)
    public R getUser(@PathVariable("id") long id) {

        User user = userService.getUserById(id);
        user.setName("测试用");
        return R.ok().setData(user);
    }

    @HandlesRequest(path = "/test/user", method = RequestMethod.POST)
    public R addUser(@RequestJsonBody @Valid  User user) {
        return R.ok().setData(user);
    }

    @HandlesRequest(path = "/test/map", method = RequestMethod.POST)
    public R echoMap(@RequestJsonBody List<User> users) {

        return R.ok().setData(users);
    }

}
