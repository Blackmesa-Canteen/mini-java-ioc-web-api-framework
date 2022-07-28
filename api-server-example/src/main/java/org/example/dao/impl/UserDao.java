package org.example.dao.impl;

import io.swen90007sm2.framework.annotation.mvc.Dao;
import org.example.dao.IUsedDao;
import org.example.entity.User;

@Dao
/**
 * access db, sql and stuff
 *
 * @author xiaotian
 */
public class UserDao implements IUsedDao {

    @Override
    public User findUserById(long id) {
        /* Mock */
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
    public boolean updateUserById(long id, User user) {
        return true;
    }

    @Override
    public boolean deleteUserById(long id) {
        return false;
    }
}
