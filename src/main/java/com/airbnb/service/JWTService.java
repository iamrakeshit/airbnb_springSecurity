package com.airbnb.service;

import com.airbnb.entity.PropartyUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    @Value("${jwt.algorithm.key}")
    private String algorithmkey;
    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiryDUration}")
    private int expiryTime;

    private Algorithm algorithm;
    @PostConstruct
    public  void postConstruct(){
        algorithm = Algorithm.HMAC256(algorithmkey);
    }

    public String generateToken(PropartyUser user){
       return JWT.create()
                .withClaim("USER_NAMR", user.getUserName())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
                .withIssuer(issuer)
                .sign(algorithm);
    }
}
