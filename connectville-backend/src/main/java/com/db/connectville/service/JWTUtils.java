package com.db.connectville.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.db.connectville.dtos.LoginRequest;
import com.db.connectville.model.User;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTUtils {
    private final Algorithm algorithm = Algorithm.HMAC256("encryptionSecret");

    public String generateJWT(User user) {
        //TO DO: populate map with user info
        Map<String, String> payloadClaims = new HashMap<>();
        payloadClaims.put("Firstname", user.getFirstName());
        payloadClaims.put("Lastname", user.getLastName());
        payloadClaims.put("Email", user.getEmail());
        payloadClaims.put("Username", user.getUsername());
        return JWT.create()
                .withPayload(payloadClaims)
                .withIssuer("issuer")
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    public boolean validate(String jwt) {
        JWTVerifier jwtVerifier = JWT.require(algorithm).build();
        try {
            jwtVerifier.verify(jwt);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean validateUser(String username, String jwt) {
        if (!validate(jwt)) {
            return false;
        }
        return JWT.decode(jwt).getSubject().equals(username);
    }
}
