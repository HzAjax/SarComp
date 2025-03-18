package ru.volodin.SarComp.controller.autorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.volodin.SarComp.entity.authorization.dto.JwtRefreshRequest;
import ru.volodin.SarComp.entity.authorization.dto.JwtRequest;
import ru.volodin.SarComp.service.autorization.AuthService;


@RestController
@RequestMapping("/api/auth")
@SuppressWarnings({"unused"})
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> auth(@RequestBody JwtRequest jwtRequest) {
        try {
            return ResponseEntity.ok(authService.generateJwtToken(jwtRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestBody JwtRefreshRequest jwtRequest) {
        try {
            return ResponseEntity.ok(authService.refreshJwtToken(jwtRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}