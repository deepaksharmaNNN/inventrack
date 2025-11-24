package com.inventrack.userservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "email", nullable = false, unique = true)
    String email;            // standardized field name

    @Column(nullable = false)
    String password;         // bcrypt hashed

    @Column(nullable = false)
    String role;             // ADMIN or USER

    @Column(name = "created_at")
    Instant createdAt;
}
