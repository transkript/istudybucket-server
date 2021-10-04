package com.feljtech.istudybucket.service;

import com.feljtech.istudybucket.entity.RefreshToken;

public interface RefreshTokenService {
    RefreshToken generateRefreshToken();

    void validateRefreshToken(String token);

    void deleteRefreshToken(String token);
}
