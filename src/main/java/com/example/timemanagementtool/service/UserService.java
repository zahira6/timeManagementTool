package com.example.timemanagementtool.service;

import com.example.timemanagementtool.exceptions.UserNotFoundException;
import com.example.timemanagementtool.model.Credentials;
import com.example.timemanagementtool.model.User;
import com.example.timemanagementtool.repo.InvalidTokensDB;
import com.example.timemanagementtool.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.timemanagementtool.repo.dao.UserDao;


@Service
public class UserService {

    @Autowired
    InvalidTokensDB invalidTokensDB;
    @Autowired
    UserDao userDB;
    @Autowired
    JwtUtil jwtUtil;

    public User addUser(User user){
        if (findUser(user.getEmail()) != null){
            return null;
        }
        else {
            if (userDB.saveUser(user)){
                return user;
            } else {
                return null;
            }
        }
    }

    public User findUser(String email){
        try {
            return userDB.findUser(email);
        } catch (UserNotFoundException e){
            return null;
        }
    }

    public String login(Credentials credentials){
        if (findUser(credentials.getEmail()) != null
                && checkPassword(credentials.getEmail(), credentials.getPassword())){
            return jwtUtil.createToken(credentials.getEmail());
        } else {
            return "";
        }
    }

    private boolean checkPassword(String email, String password){
        if (password.isBlank() || email.isBlank()){
            return false;
        } else {
            try {
                var checkUser = userDB.findUser(email);
                return checkUser != null && checkUser.getPassword().equals(password);
            } catch (UserNotFoundException e) {
                return false;
            }
        }
    }

    public boolean logout(String token){
        if (token.isBlank()){
            return false;
        } else {
            return invalidTokensDB.invalidateToken(token);
        }
    }
}
