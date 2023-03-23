package com.school.reyfow.auth;

import io.jsonwebtoken.Jwts;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class JwtTokenUtil {

    private final String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP512lm2123113513nlm15161515";

    public String getUsername(String token) {
        var claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validate(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecret.getBytes())
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (Exception exception) {
            return false;
        }

    }
}
