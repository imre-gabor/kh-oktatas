package com.khb.hu.springcourse.hr.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Service
public class JwtService {

    public static final String HR_APP = "HrApp";
    public static final String AUTH = "auth";
    private Algorithm alg = Algorithm.HMAC512("mysecret");

    public String createJwt(UserDetails userDetails){
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withArrayClaim(AUTH,
                        userDetails.getAuthorities()
                                .stream()
                                .map(GrantedAuthority::getAuthority)
                                .toArray(String[]::new))
                .withIssuer(HR_APP)
                .withExpiresAt(OffsetDateTime.now().plusHours(24).toInstant())
                .sign(alg);
    }

    public UserDetails parseJwt(String jwt) {

        DecodedJWT decodedJwt = JWT.require(alg)
                .withIssuer(HR_APP)
                .build()
                .verify(jwt);

        return new User(decodedJwt.getSubject(), "dummy",
                decodedJwt.getClaim(AUTH).asList(String.class)
                        .stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList()
        );
    }
}
