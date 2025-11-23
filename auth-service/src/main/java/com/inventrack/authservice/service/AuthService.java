package com.inventrack.authservice.service;

import com.inventrack.authservice.jwt.JwtUtil;
import com.inventrack.authservice.model.LoginRequest;
import com.inventrack.authservice.model.LoginResponse;
import com.inventrack.authservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;

    private final User tempUser =
            new User("admin@admin.com", "admin", "ADMIN");

    public LoginResponse login(LoginRequest request){
        if(!request.getEmail().equals(tempUser.getUsername()) ||
            !request.getPassword().equals(tempUser.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        Map<String,Object> claims = new HashMap<>();
        claims.put("role", tempUser.getRole());
        String token = jwtUtil.generateToken(tempUser.getUsername(), claims);
        return new LoginResponse(token);
    }
}
