package org.example.handler;

import io.swen90007sm2.framework.annotation.ioc.AutoInjected;
import io.swen90007sm2.framework.annotation.mvc.*;
import io.swen90007sm2.framework.bean.R;
import io.swen90007sm2.framework.common.constant.RequestMethod;
import org.example.entity.User;
import org.example.blo.IUserBlo;

@Handler(path = "/admin")
public class UserHandler {

    @AutoInjected
    private IUserBlo userService;

    @HandlesRequest(path = "/", method = RequestMethod.GET)
    public R getUserRoot() {

        User user = userService.getUserById(2323232L);
        return R.ok().setData(user);
    }

    @HandlesRequest(path = "/user/{id}", method = RequestMethod.GET)
    public R getUser(@PathVariable("id") Long id) {

        User user = userService.getUserById(id);
        return R.ok().setData(user);
    }

    @HandlesRequest(path = "/user", method = RequestMethod.GET)
    public R getUser(@RequestParam(value = "name") String name, @RequestParam("id") Long id, @RequestParam("age") Integer age) {
        User user = new User(id, name, age);

        return R.ok().setData(user);
    }

    @HandlesRequest(path = "/user", method = RequestMethod.POST)
    public R addUser(@RequestJsonBody User user) {
        boolean res = userService.addUser(user);

        if (res) {
            return R.ok().setData(user);
        } else {
            return R.error("err");
        }
    }

}
