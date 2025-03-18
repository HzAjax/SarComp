package ru.volodin.SarComp.service.autorization;


import org.springframework.lang.Nullable;
import ru.volodin.SarComp.entity.authorization.TokenType;
import ru.volodin.SarComp.entity.authorization.User;
import io.jsonwebtoken.Claims;
import ru.volodin.SarComp.entity.authorization.dto.JwtAuthentication;

import javax.crypto.SecretKey;

@SuppressWarnings({"unused"})
public interface JwtService {
    String generateToken(TokenType type, String subject, @Nullable User user);
    boolean validateToken(TokenType type, String token);
    SecretKey getSecretKey(TokenType type);
    Long getExpiration(TokenType type);
    Claims getClaims(TokenType type, String token);
    JwtAuthentication authenticate(Claims claims);
}
