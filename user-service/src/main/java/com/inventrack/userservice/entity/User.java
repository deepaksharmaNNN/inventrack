package com.inventrack.userservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String name;

    @Email @Column(unique=true, nullable=false)
    String username;

    @Column(nullable=false)
    String password; // stored as BCrypt

    @Column(nullable=false)
    String role; // e.g. ROLE_USER or ROLE_ADMIN

    @Column(nullable = false, updatable = false)
    Instant createdAt;
}
