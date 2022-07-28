package org.example.blo;

import org.example.entity.User;

public interface IUserBlo {
    User getUserById(Long id);
    boolean addUser(User user);
    boolean updateUserById(Long id, User user);
}
