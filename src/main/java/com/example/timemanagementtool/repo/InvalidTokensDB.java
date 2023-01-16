package com.example.timemanagementtool.repo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class InvalidTokensDB {
    public ArrayList<String> invalidTokens = new ArrayList<>();

    public boolean invalidateToken(String token){
        if (token.isBlank()){
            return false;
        } else {
            return invalidTokens.add(token);
        }
    }
}
