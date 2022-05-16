package com.elroykanye.istudybucket.data.repository;

import com.elroykanye.istudybucket.data.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByTokenValue(String verificationTokenValue);
}
