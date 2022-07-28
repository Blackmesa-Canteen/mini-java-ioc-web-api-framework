package org.example.service.impl;

import org.example.dao.IUsedDao;
import org.example.entity.User;
import org.example.service.IUserService;
import io.swen90007sm2.framework.annotation.*;

@Service
public class UserService implements IUserService {

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
