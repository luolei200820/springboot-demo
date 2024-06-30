package org.eu.luolei;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void generate() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", "luolei");
        claims.put("id", 1);

        String token = JWT.create()
                .withClaim("user", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12)) // 12个小时
                .sign(Algorithm.HMAC256("123456"));

        System.out.println(token);
    }

    @Test
    public void validate() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6Imx1b2xlaSJ9LCJleHAiOjE3MTk3OTc5Nzd9.ODFLJlUBX3qXGpzCEddgkLk6cbICgSowjPzHv04-aGs";

        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("123456")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);

        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));
    }
}
