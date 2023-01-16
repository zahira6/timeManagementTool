package com.example.timemanagementtool.utils;

import com.example.timemanagementtool.repo.InvalidTokensDB;
import io.fusionauth.jwt.InvalidJWTException;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@Service
public class JwtUtil {
    String emailClaim = "email";

    @Autowired
    InvalidTokensDB invalidTokensDB;
    @Value("${timeManagementTool.accessKey}")
    private String accessKey;

    public String createToken(String email){
        var signer = HMACSigner.newSHA256Signer(accessKey);

        var jwt = new JWT()
                .addClaim(emailClaim, email)
                .setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
                .setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusHours(5));

        return JWT.getEncoder().encode(jwt, signer);
    }

    public Boolean validateToken(String token){
        if (invalidTokensDB.invalidTokens.contains(token)){
            return false;
        }
        var verifier = HMACVerifier.newVerifier(accessKey);
        try {
            var jwt = JWT.getDecoder().decode(token, verifier);
            if (jwt.isExpired()){
                invalidTokensDB.invalidateToken(token);
                return false;
            }
        } catch (InvalidJWTException e) {
            return false;
        }
        return true;
    }

}
