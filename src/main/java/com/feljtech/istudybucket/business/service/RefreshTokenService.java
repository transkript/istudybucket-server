package com.feljtech.istudybucket.business.service;

import com.feljtech.istudybucket.data.entity.RefreshToken;

public interface RefreshTokenService {
    RefreshToken generateRefreshToken();

    void validateRefreshToken(String token);

    void deleteRefreshToken(String token);
}
