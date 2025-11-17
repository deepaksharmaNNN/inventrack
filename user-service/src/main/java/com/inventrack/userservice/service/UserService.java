package com.inventrack.userservice.service;

import com.inventrack.userservice.dtos.UserDto;
import com.inventrack.userservice.entity.User;
import com.inventrack.userservice.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Var;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;


    public User createUser(@Valid UserDto userDto) {
        // Map UserDto to User entity
        var user = User.builder()
                .name(userDto.getName())
                .username(userDto.getEmail())
                .role(userDto.getRole())
                .createdAt(java.time.Instant.now())
                .build();
        // Save the user entity to the database
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElse(null);
    }
    // delete user by id
    public String deleteUserById(Long id) {
        User user = getUserById(id);
        if (user != null) {
            userRepository.deleteById(id);
            return "User with id " + id + " deleted successfully.";
        } else {
            return "User with id " + id + " not found.";
        }
    }
}
