package com.elroykanye.istudybucket.business.service;

import com.elroykanye.istudybucket.api.dto.request.LoginRequest;
import com.elroykanye.istudybucket.api.dto.request.RegisterRequest;
import com.elroykanye.istudybucket.api.dto.response.RegisterResponse;
import com.elroykanye.istudybucket.config.jwt.JwtRefreshTokenRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    RegisterResponse registerAccount(RegisterRequest registerRequest);

    boolean verifyAccount(String verificationToken, String username);

    ResponseEntity<?> loginUser(LoginRequest loginRequest) throws Exception;

    ResponseEntity<?> refreshToken(JwtRefreshTokenRequest jwtRefreshTokenRequest);

    ResponseEntity<String> logoutUser(JwtRefreshTokenRequest jwtRefreshTokenRequest);
}
