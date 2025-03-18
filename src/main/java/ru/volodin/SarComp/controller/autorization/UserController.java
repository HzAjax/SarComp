package ru.volodin.SarComp.controller.autorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.volodin.SarComp.entity.authorization.User;
import ru.volodin.SarComp.service.autorization.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@SuppressWarnings({"unused"})
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody User user) {
        try {
            User newUser = userService.addUser(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUsers(@PathVariable("userId") UUID userId) {
        try {
            return ResponseEntity.ok(userService.getUserById(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<?> deleteUserById(@PathVariable("userId") UUID userId) {
        User deletedUser = userService.getUserById(userId);
        userService.deleteUserById(userId);
        return ResponseEntity.ok(deletedUser);
    }
}