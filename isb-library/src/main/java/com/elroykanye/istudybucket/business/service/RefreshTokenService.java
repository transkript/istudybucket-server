package com.elroykanye.istudybucket.business.service;

import com.elroykanye.istudybucket.data.entity.RefreshToken;

public interface RefreshTokenService {
    RefreshToken generateRefreshToken();

    void validateRefreshToken(String token);

    void deleteRefreshToken(String token);
}
