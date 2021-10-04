package com.feljtech.istudybucket.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class JwtResponse {
    private static final long serialVersionUID = -8091879091924046844L;
    private String jwtToken;
    private String refreshToken;
    private String username;
    private Instant expiresAt;

}
