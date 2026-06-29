package com.foodapp.dao;

import java.util.List;
import com.foodapp.model.User;

public interface UserDAO {

    int addUser(User user);

    User login(String email, String password);

    User getUserById(int userId);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    int updateUser(User user);

    int deleteUser(int userId);
}