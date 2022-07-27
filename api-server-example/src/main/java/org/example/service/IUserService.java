package org.example.service;

import org.example.entity.User;

public interface IUserService {
    User getUserById(Long id);
    boolean addUser(User user);
    boolean updateUserById(Long id, User user);
}
