package com.example.timemanagementtool.repo;

import com.example.timemanagementtool.exceptions.UserNotFoundException;
import com.example.timemanagementtool.model.User;
import com.example.timemanagementtool.repo.dao.UserDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Objects;

@Repository
public class UserDB implements UserDao {
    ArrayList<User> users = new ArrayList<>();

    @Override
    public boolean saveUser(User user) {
        if (user != null){
            users.add(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User findUser(String email) throws UserNotFoundException {
        if (email != null){
            return users.stream()
                    .filter(user -> Objects.equals(user.getEmail(), email))
                    .findFirst()
                    .orElseThrow(() -> new UserNotFoundException("User not found"));
        } else {
            return null;
        }

    }
}
