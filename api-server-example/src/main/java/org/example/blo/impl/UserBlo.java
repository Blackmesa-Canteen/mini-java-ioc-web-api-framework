package org.example.blo.impl;

import io.swen90007sm2.framework.annotation.ioc.AutoInjected;
import io.swen90007sm2.framework.annotation.mvc.Blo;
import org.example.blo.IOrderBlo;
import org.example.dao.IUsedDao;
import org.example.entity.User;
import org.example.blo.IUserBlo;

@Blo
public class UserBlo implements IUserBlo {

    @AutoInjected
    IUsedDao userDao;

    @AutoInjected
    IOrderBlo orderBlo;

    @Override
    public User getUserById(Long id) {
        User user = userDao.findUserById(id);
        orderBlo.addOrder(2);
        return user;
    }

    @Override
    public boolean addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public boolean updateUserById(Long id, User user) {
        return userDao.updateUserById(id, user);
    }
}
