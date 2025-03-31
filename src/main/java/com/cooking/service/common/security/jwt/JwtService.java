package com.cooking.service.common.security.jwt;

import com.cooking.dao.model.user.User;
import com.cooking.service.common.security.user.SecurityUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

import static io.jsonwebtoken.Jwts.SIG.HS512;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.validity-in-seconds}")
    private int validityInSeconds;

    private final SecretKey secretKey;

    public Claims extractClaims(String token) {
        var parser = Jwts
                .parser()
                .verifyWith(secretKey)
                .build();
        try {
            return parser.parseSignedClaims(token).getPayload();
        } catch (JwtException e) {
            return null;
        }
    }

    public Authentication getAuthentication(Claims claims) {
        var userId = claims.get("userId", String.class);
        var email = claims.get("email", String.class);
        var fullName = claims.get("fullName", String.class);
        var role = claims.get("role", String.class);
        var authority = new SimpleGrantedAuthority(role);
        var securityUser = new SecurityUser(email, "no-password", List.of(authority), userId, email, fullName);
        return new UsernamePasswordAuthenticationToken(securityUser, "no-password", List.of(authority));
    }

    public String createToken(User user) {

        var now = new Date();
        var until = Date.from(now.toInstant().plusSeconds(validityInSeconds));

        return Jwts.builder()
                .claim("userId", user.getId())
                .claim("email", user.getEmail())
                .claim("fullName", user.getFirstName() + " " + user.getLastName())
                .claim("role", user.getRole().getValue())
                .issuedAt(new Date())
                .expiration(until)
                .signWith(secretKey, HS512)
                .compact();
    }
}
