package com.feljtech.istudybucket.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtRefreshTokenRequest {
    @NotBlank
    private String refreshToken;
    private String username;

}
