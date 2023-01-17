package com.example.timemanagementtool.controller;

import com.example.timemanagementtool.model.Credentials;
import com.example.timemanagementtool.model.User;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.timemanagementtool.service.UserService;

import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/user")
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
        JSONObject jsonToken = new JSONObject();
        jsonToken.put("access_token", token);
        return ResponseEntity.ok(jsonToken.toString());
    }

    @PostMapping("/register")
    ResponseEntity<String> register(
            @RequestBody Credentials credentials) {
        if (userService.addUser(new User(credentials)) != null) {
            JSONObject jsonRegister = new JSONObject();
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
