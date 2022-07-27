package org.example.service.impl;

import io.swen90007sm2.bean.R;
import org.example.entity.User;
import org.example.service.IUserService;
import io.swen90007sm2.annotation.*;

@Service
public class UserService implements IUserService {

    @Override
    public User getUserById(Long id) {
        User demoData = new User();
        demoData.setId(id);
        demoData.setAge(23);
        demoData.setName("abc");

        return demoData;
    }

    @Override
    public boolean addUser(User user) {
        return true;
    }

    @Override
    public boolean updateUserById(Long id, User user) {
        return false;
    }
}
