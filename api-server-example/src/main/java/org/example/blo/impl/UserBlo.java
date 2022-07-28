package org.example.blo.impl;

import org.example.dao.IUsedDao;
import org.example.entity.User;
import org.example.blo.IUserBlo;
import io.swen90007sm2.framework.annotation.*;

@Blo
public class UserBlo implements IUserBlo {

    @AutoInjected
    IUsedDao userDao;

    @Override
    public User getUserById(Long id) {
        User user = userDao.findUserById(id);

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
