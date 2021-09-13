package com.feljtech.istudybucket.repository;

import com.feljtech.istudybucket.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
}
