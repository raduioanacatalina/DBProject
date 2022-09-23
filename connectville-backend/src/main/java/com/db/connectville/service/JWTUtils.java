package com.db.connectville.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.db.connectville.dtos.LoginRequest;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTUtils {
    private final Algorithm algorithm = Algorithm.HMAC256("encryptionSecret");

    public String generateJWT(LoginRequest loginRequest) {
        return JWT.create()
                .withSubject(loginRequest.getUsername())
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
