package com.ShoeVibes.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class JWTUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long  expiration = 86400000;

    //Token Üretme
    public String generateToken(String email,String role) {
        return JWT.create()
                .withSubject(email)
                .withClaim("role",role)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + expiration))
                .sign(Algorithm.HMAC512(secret.getBytes()));
    }

    //Tokenden Kullanıcı Almak
    public String extractUsername(String token){
        return JWT.require(Algorithm.HMAC512(secret.getBytes()))
                .build()
                .verify(token)
                .getSubject();
    }

    //Tokenden Rol Almak
    public String extractRole(String token){
        return JWT.require(Algorithm.HMAC512(secret.getBytes()))
                .build()
                .verify(token)
                .getClaim("role")
                .asString();
    }

    //Tokenin Geçerliliğini Kontrol Etme
    public boolean isTokenExpired(String token){
        Date expiration = JWT.require(Algorithm.HMAC512(secret.getBytes()))
                .build()
                .verify(token)
                .getExpiresAt();
        return expiration.before(new Date());
    }

    //Tokenin Geçerliliğini ve Kullanıcıyı Kontrol Etme
    public Boolean validateToken(String token,String username){
        String tokenUsername = extractUsername(token);
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    // JWT'yi decode etme
    public DecodedJWT decodeJWT(String token) {
        try {
            return JWT.require(Algorithm.HMAC512(secret.getBytes()))
                    .build()
                    .verify(token);
        } catch (JWTDecodeException e) {
            return null; // Token geçersizse null döner
        }
    }
}
