package com.feljtech.istudybucket.repository;

import com.feljtech.istudybucket.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByTokenValue(String verificationTokenValue);
}
