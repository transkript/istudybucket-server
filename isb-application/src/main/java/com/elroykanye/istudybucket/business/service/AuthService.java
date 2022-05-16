package com.elroykanye.istudybucket.business.service;

import com.elroykanye.istudybucket.api.dto.request.LoginRequest;
import com.elroykanye.istudybucket.api.dto.request.RegisterRequest;
import com.elroykanye.istudybucket.api.dto.response.LoginResponse;
import com.elroykanye.istudybucket.api.dto.response.LogoutResponse;
import com.elroykanye.istudybucket.api.dto.response.RegisterResponse;
import com.elroykanye.istudybucket.config.jwt.JwtRefreshTokenRequest;

public interface AuthService {
    RegisterResponse registerAccount(RegisterRequest registerRequest);

    boolean verifyAccount(String verificationToken, String username);

    LoginResponse loginUser(LoginRequest loginRequest) throws Exception;

    LoginResponse refreshToken(JwtRefreshTokenRequest jwtRefreshTokenRequest);

    LogoutResponse logoutUser(JwtRefreshTokenRequest jwtRefreshTokenRequest);
}
