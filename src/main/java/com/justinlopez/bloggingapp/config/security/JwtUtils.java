package com.justinlopez.bloggingapp.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class JwtUtils {

    private JwtUtils() {}

    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();

    public static boolean validateToken(String token) {
        return parseToken(token).isPresent();
    }

    private static Optional<Claims> parseToken(String token) {
        var jwtParser = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build();

        try {
            return Optional.of(jwtParser.parseSignedClaims(token).getPayload());
        } catch (JwtException | IllegalArgumentException e) {
            log.error("A Jwt Exception occurred");
        }

        return Optional.empty();
    }

    public static Optional<String> getUsernameFromToken(String token) {
        Optional<Claims> claims = parseToken(token);
        return claims.map(Claims::getSubject);
    }


    public static String generateToken(String username) {
        Date currentDate = new Date();
        int jwtExpirationInMinutes = 10;

        Date expiration = DateUtils.addMinutes(currentDate, jwtExpirationInMinutes);

        return Jwts.builder()
                .id(UUID.randomUUID().toString())
                .issuer("justin_lopez")
                .subject(username)
                .signWith(SECRET_KEY)
                .issuedAt(currentDate)
                .expiration(expiration)
                .compact();
    }
}
