package com.thales.thales_api.controller;

import com.thales.thales_api.dto.auth.AuthRequestDTO;
import com.thales.thales_api.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Value("${app.auth.username}")
    private String validUsername;

    @Value("${app.auth.password}")
    private String validPassword;

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO request) {
        if (validUsername.equals(request.getUsername()) && validPassword.equals(request.getPassword())) {
            Map<String, String> resp = new HashMap<>();
            resp.put("token", jwtUtil.getJWTToken(request.getUsername()));
            return ResponseEntity.ok(resp);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

}
