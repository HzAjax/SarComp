package ru.volodin.SarComp.service.autorization.impl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.volodin.SarComp.entity.authorization.Role;
import ru.volodin.SarComp.entity.authorization.TokenType;
import ru.volodin.SarComp.entity.authorization.User;
import ru.volodin.SarComp.entity.authorization.dto.JwtAuthentication;
import ru.volodin.SarComp.repository.autorization.RoleRepository;
import ru.volodin.SarComp.service.autorization.JwtService;

import javax.crypto.SecretKey;
import java.util.*;

@Slf4j
@Service
@SuppressWarnings({"unused"})
public class JwtServiceImpl implements JwtService {

    private static final String ROLE_CLAIM_KEY = "role";

    @Autowired
    private RoleRepository roleRepository;

    @Value("${jwt.access.secret}")
    protected String accessSecret;
    @Value("${jwt.refresh.secret}")
    protected String refreshSecret;

    @Value("${jwt.access.exp_time}")
    protected String accessExpirationTime;
    @Value("${jwt.refresh.exp_time}")
    protected String refreshExpirationTime;

    @Override
    public String generateToken(TokenType type, String subject, @Nullable User user) {
        Date date = new Date();

        JwtBuilder builder = Jwts.builder()
                .signWith(getSecretKey(type), Jwts.SIG.HS512)
                .subject(subject)
                .issuedAt(date)
                .expiration(new Date(date.getTime() + getExpiration(type)));

        if (user != null) {
            builder.claim(ROLE_CLAIM_KEY, user.getRole().getAuthority());
        }

        return builder.compact();
    }

    @Override
    public boolean validateToken(TokenType type, String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey(type))
                    .build()
                    .parseSignedClaims(token);

            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired", expEx);
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt", unsEx);
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt", mjEx);
        } catch (Exception e) {
            log.error("Invalid token", e);
        }
        return false;
    }

    @Override
    public SecretKey getSecretKey(TokenType type) {
        byte[] encodeKey = Base64.getDecoder().decode(TokenType.ACCESS == type ? accessSecret : refreshSecret);
        return Keys.hmacShaKeyFor(encodeKey);
    }

    @Override
    public Long getExpiration(TokenType type) {
        return Long.parseLong(TokenType.ACCESS == type ? accessExpirationTime : refreshExpirationTime);
    }

    @Override
    public Claims getClaims(TokenType type, String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey(type))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public JwtAuthentication authenticate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(List.of(Role.builder()
                .name(claims.get(ROLE_CLAIM_KEY, String.class))
                .build()));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }
}