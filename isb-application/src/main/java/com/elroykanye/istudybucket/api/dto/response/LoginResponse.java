package com.elroykanye.istudybucket.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Builder
@Getter
@Setter
public class LoginResponse {
    @JsonProperty("access_token")
    private String jwtToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("username")
    private String username;
    @JsonProperty("expires_in")
    private Instant expiresAt;
}
