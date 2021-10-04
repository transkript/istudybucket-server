package com.feljtech.istudybucket.business.service.impl;

import com.feljtech.istudybucket.data.entity.RefreshToken;
import com.feljtech.istudybucket.config.excetion.IstudybucketException;
import com.feljtech.istudybucket.data.repository.RefreshTokenRepository;
import com.feljtech.istudybucket.business.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken =  RefreshToken.builder()
                .refreshToken(UUID.randomUUID().toString())
                .createdDate(Instant.now()).build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public void validateRefreshToken(String token) {
        refreshTokenRepository.findByRefreshToken(token)
                .orElseThrow(() -> new IstudybucketException("Invalid refresh token"));
    }

    @Override
    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByRefreshToken(token);
    }
}
