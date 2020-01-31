package com.sg.hellosecurity.dao;

import com.sg.hellosecurity.entities.User;
import java.util.List;

/**
 * @date Friday January 31, 2020
 * @author garrettbecker
 */

public interface UserDao {
    User getUserById(int id);
    User getUserByUsername(String username);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUser(int id);
    User createUser(User user);
}
