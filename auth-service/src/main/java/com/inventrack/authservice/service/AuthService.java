package com.inventrack.authservice.service;

import com.inventrack.authservice.client.UserClient;
import com.inventrack.authservice.dto.UserDto;
import com.inventrack.authservice.jwt.JwtUtil;
import com.inventrack.authservice.model.LoginRequest;
import com.inventrack.authservice.model.LoginResponse;
import com.inventrack.authservice.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtUtil jwtUtil;
    private final UserClient userClient;
    private final PasswordEncoder passwordEncoder;

    private final User tempUser =
            new User("admin@admin.com", "admin", "ADMIN");

    public LoginResponse login(LoginRequest request){
        UserDto user = userClient.getUserByEmail(request.getEmail());
        if (user == null){
            throw new RuntimeException("Invalid username/password");
        }
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid username/password");
        }
        Map<String,Object> claims = Map.of(
                "role", user.getRole(),
                "userId", user.getId()
        );
        String token = jwtUtil.generateToken(user.getEmail(), claims);
        return new LoginResponse(token);
    }
}
