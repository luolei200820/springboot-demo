package org.eu.luolei.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Map;

public class JwtUtils {

    protected static JWTVerifier jwtVerifier = null;

    static {
        jwtVerifier = JWT.require(Algorithm.HMAC256("123456")).build();
    }

    public static Map<String, Claim> parseToken(String token) {
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getClaims();
    }
}
