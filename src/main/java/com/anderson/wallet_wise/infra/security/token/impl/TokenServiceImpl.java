package com.anderson.wallet_wise.infra.security.token.impl;

import com.anderson.wallet_wise.infra.exceptions.TokenException;
import com.anderson.wallet_wise.infra.security.token.ITokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.UUID;

public class TokenServiceImpl implements ITokenService {

    @Value("${spring.security.secret}")
    private String secret;

    @Value("${spring.security.expiration}")
    private String expiration;

    @Override
    public String generate(UUID id) {
        try {
            return JWT.create()
                    .withIssuer("auth")
                    .withSubject(id.toString())
                    .withExpiresAt(new Date(new Date().getTime() + Long.parseLong(expiration)))
                    .sign(Algorithm.HMAC256(secret));
        } catch (JWTCreationException e) {
            throw new TokenException(e.toString());
        }
    }

    @Override
    public UUID extractId(String token) {
        try {
            String subject = JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("auth")
                    .build()
                    .verify(token)
                    .getSubject();
            return UUID.fromString(subject);
        } catch (Exception e) {
            throw new TokenException(e.toString());
        }
    }
}
