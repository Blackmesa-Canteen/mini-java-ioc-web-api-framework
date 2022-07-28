package org.example.dao;

import org.example.entity.User;

public interface IUsedDao {

    User findUserById(long id);
    boolean addUser(User user);
    boolean updateUserById(long id, User user);
    boolean deleteUserById(long id);
}
