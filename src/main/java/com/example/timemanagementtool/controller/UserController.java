package com.example.timemanagementtool.controller;

import com.example.timemanagementtool.model.Credentials;
import com.example.timemanagementtool.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.timemanagementtool.service.UserService;

import java.util.HashMap;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    ResponseEntity<String> login(
            @RequestBody Credentials credentials){
        var token = userService.login(credentials);
        if (token.isBlank()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        HashMap<String, String> jsonToken = new HashMap<>();
        jsonToken.put("access_token", token);
        return ResponseEntity.ok(jsonToken.toString());
    }

    @PostMapping("/register")
    ResponseEntity<String> register(
            @RequestBody Credentials credentials) {
        if (userService.addUser(new User(credentials)) != null) {
            HashMap<String, Boolean> jsonRegister = new HashMap<>();
            jsonRegister.put("Registration", true);
            return ResponseEntity.ok(jsonRegister.toString());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/logout")
    ResponseEntity<String> logout (
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        if (token.isBlank()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else {
            if (userService.logout(token)){
                return ResponseEntity.ok("Logout was successful");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
    }
}
