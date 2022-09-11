package com.laconcierge.ocupacao.util;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtil {
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SECRET = System.getenv("jwt_secret");
    private static final JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET))
            .acceptExpiresAt(0)
            .build();
    
    public static Boolean isTokenOnHeader(HttpServletRequest request) {
        return request.getHeader(HEADER_STRING) != null && request.getHeader(HEADER_STRING).startsWith(TOKEN_PREFIX); 
    }
    
    public static Boolean isTokenValid(String token) {
        try{
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
        
    }
    
    public static String getJWTFromRequest(HttpServletRequest request) {
        return request.getHeader(HEADER_STRING).replace(TOKEN_PREFIX,"");
    }
    
    public static UUID getSubjectFromToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return UUID.fromString(decodedJWT.getSubject());
    }
    
}
