package com.inventrack.jwtcore.jwt;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "jwt")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtProperties {
    String secret;
    long expiration;
}
