package com.inventrack.authservice.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UserDto {
    Long id;
    String email;
    String password;
    String role;
}
