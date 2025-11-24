package com.inventrack.userservice.service;

import com.inventrack.userservice.dtos.UserDto;
import com.inventrack.userservice.entity.User;
import com.inventrack.userservice.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(@Valid UserDto userDto) {
        var user = User.builder()
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .createdAt(Instant.now())
                .build();
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public String deleteUserById(Long id) {
        User user = getUserById(id);
        if (user != null) {
            userRepository.deleteById(id);
            return "User with id " + id + " deleted successfully.";
        } else {
            return "User with id " + id + " not found.";
        }
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
