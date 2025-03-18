package ru.volodin.SarComp.service.autorization;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.volodin.SarComp.entity.authorization.TokenType;
import ru.volodin.SarComp.entity.authorization.User;
import ru.volodin.SarComp.entity.authorization.dto.JwtRefreshRequest;
import ru.volodin.SarComp.entity.authorization.dto.JwtRequest;
import ru.volodin.SarComp.entity.authorization.dto.JwtResponse;
import ru.volodin.SarComp.repository.autorization.UserRepository;

import java.util.Optional;

@Service
@SuppressWarnings({"unused"})
public class AuthService {

    @Autowired
    protected JwtService jwtService;
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected AuthenticationManager authenticationManager;

    public JwtResponse generateJwtToken(JwtRequest jwtReq) {
        Optional<User> user = userRepository.findByLogin(jwtReq.getLogin());
        if (user.isPresent()) {
            if (!passwordEncoder.matches(jwtReq.getPassword(), user.get().getPassword()))
                throw new RuntimeException("Неверный пароль");
        } else {
            throw new RuntimeException("Пользователь не найден");
        }

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.get().getUsername(), jwtReq.getPassword()));

        return new JwtResponse(jwtService.generateToken(TokenType.ACCESS, user.get().getLogin(), user.get()),
                jwtService.generateToken(TokenType.REFRESH, user.get().getLogin(), null));
    }

    public JwtResponse refreshJwtToken(JwtRefreshRequest jwtReq) {
        Claims claims = jwtService.getClaims(TokenType.REFRESH, jwtReq.getRefreshToken());
        Optional<User> user = userRepository.findByLogin(claims.getSubject());
        if (user.isEmpty()) {
            throw new RuntimeException("Пользователь не найден");
        } else {
            try {
                if (!jwtService.validateToken(TokenType.REFRESH, jwtReq.getRefreshToken())) {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                throw new RuntimeException("Refresh-token просрочен");
            }

            return new JwtResponse(jwtService.generateToken(TokenType.ACCESS, user.get().getLogin(), user.get()),
                    jwtService.generateToken(TokenType.REFRESH, user.get().getLogin(), null));
        }
    }
}
