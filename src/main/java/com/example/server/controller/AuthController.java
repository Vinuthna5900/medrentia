package com.example.server.controller;

import com.example.server.dto.LoginRequest;
import com.example.server.dto.LoginResponse;
import com.example.server.dto.UserResponse;
import com.example.server.model.User;
import com.example.server.service.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 🔐 REGISTER
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody User user) {

    User savedUser = authService.register(user);

    UserResponse response = new UserResponse(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getEmail(),
            savedUser.getRole()
    );

    return ResponseEntity.ok(response);
}  
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    try {
        String token = authService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new LoginResponse(token));
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
}
