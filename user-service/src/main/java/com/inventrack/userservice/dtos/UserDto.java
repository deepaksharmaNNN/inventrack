package com.inventrack.userservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.inventrack.userservice.entity.User}
 */
@Value
public class UserDto implements Serializable {
    @NotBlank
    String name;
    @Email
    @NotBlank
    String email;
    @NotBlank
    String role;
}