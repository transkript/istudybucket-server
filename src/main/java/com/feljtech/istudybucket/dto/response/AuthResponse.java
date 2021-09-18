package com.feljtech.istudybucket.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    @JsonProperty(value = "auth_token")
    private String authToken;
    @JsonProperty(value = "username")
    private String username;
}
