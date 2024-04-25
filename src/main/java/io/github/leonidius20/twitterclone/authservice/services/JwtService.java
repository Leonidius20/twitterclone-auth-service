package io.github.leonidius20.twitterclone.authservice.services;

import io.github.leonidius20.twitterclone.authservice.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.HashMap;

@Service
public class JwtService {

    @Value("${jwt.signing.key}")
    private String jwtSigningKey;

    public String generateToken(User user) {
        var claims = new HashMap<String, Object>();
        claims.put("user_id", user.getId());
        claims.put("username", user.getUsername());

        return Jwts.builder()
                .setClaims(claims)
                //.setSubject(user.getUsername())
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
