package com.elroykanye.istudybucket.business.service.impl;

import com.elroykanye.istudybucket.business.service.RefreshTokenService;
import com.elroykanye.istudybucket.data.entity.RefreshToken;
import com.elroykanye.istudybucket.data.repository.RefreshTokenRepository;
import com.elroykanye.istudybucket.excetion.IStudyBucketException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken =  RefreshToken.builder()
                .refreshToken(UUID.randomUUID().toString())
                .createdDate(LocalDateTime.now()).build();

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public void validateRefreshToken(String token) {
        refreshTokenRepository.findByRefreshToken(token)
                .orElseThrow(() -> new IStudyBucketException("Invalid refresh token"));
    }

    @Override
    public void deleteRefreshToken(String token) {
        log.info("Deleting refresh token: {}", token);

        refreshTokenRepository.findByRefreshToken(token).ifPresentOrElse((refreshToken) -> {
            log.info("Deleted refresh token: {}", refreshToken);

            refreshTokenRepository.delete(refreshToken);
        }, () -> {
            log.info("Refresh token not found: {}", token);

            throw new IStudyBucketException.RefreshTokenException(token);
        });
    }
}
