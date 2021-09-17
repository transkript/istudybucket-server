package com.feljtech.istudybucket.service.impl;

import com.feljtech.istudybucket.dto.RegisterForm;
import com.feljtech.istudybucket.entity.User;
import com.feljtech.istudybucket.entity.VerificationToken;
import com.feljtech.istudybucket.dto.email.NotificationEmail;
import com.feljtech.istudybucket.repository.UserRepository;
import com.feljtech.istudybucket.repository.VerificationTokenRepository;
import com.feljtech.istudybucket.service.AuthService;
import com.feljtech.istudybucket.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    @Override
    @Transactional
    public void register(RegisterForm registerForm, HttpServletRequest request) {
        User newUser = User.builder() // build the new User object from the register form
                .username(registerForm.getUsername())
                .email(registerForm.getEmail())
                .password(this.encodePassword(registerForm.getPassword())) // encode password
                .creationDate(Instant.now())
                .userEnabled(Boolean.FALSE)
                .build();

        userRepository.save(newUser); // save user to database

        String verToken = this.generateVerificationToken(newUser);
        String activationUrl = request.getContextPath();
        mailService.sendEmail(
                NotificationEmail.builder()
                .body("Activate account: " + activationUrl + " : " + verToken)
                .subject("Verify Email")
                .recipient(newUser.getEmail())
                .build()
        );
    }

    private String generateVerificationToken(User newUser) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder() // build the verfication token object
                .tokenValue(token)
                .user(newUser)
                .build();
        verificationTokenRepository.save(verificationToken); // save to the table
        return token;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
