package org.example.springinitializr.controller;

import org.example.springinitializr.dto.AuthRequest;
import org.example.springinitializr.dto.MessageResponse;
import org.example.springinitializr.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // POST /api/auth/register
    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@RequestBody AuthRequest req) {
        try {
            userService.register(req.getUsername(), req.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new MessageResponse("User registered successfully"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        } catch (IllegalStateException e) {
            // duplicate username
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new MessageResponse("Username already exists"));
        }
    }

    // POST /api/auth/login
    @PostMapping("/login")
    public ResponseEntity<MessageResponse> login(@RequestBody AuthRequest req) {
        boolean ok = userService.login(req.getUsername(), req.getPassword());
        if (ok) {
            return ResponseEntity.ok(new MessageResponse("Login successful"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("Invalid username or password"));
        }
    }
}
