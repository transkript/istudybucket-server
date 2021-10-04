package com.feljtech.istudybucket.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

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
