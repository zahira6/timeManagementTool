package com.example.timemanagementtool.repo.dao;

import com.example.timemanagementtool.exceptions.UserNotFoundException;
import com.example.timemanagementtool.model.User;

public interface UserDao {
    boolean saveUser (User user);
    User findUser (String email) throws UserNotFoundException;

}
