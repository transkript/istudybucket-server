package com.feljtech.istudybucket.service.impl;

import com.feljtech.istudybucket.dto.email.DefaultEmail;
import com.feljtech.istudybucket.dto.email.VerificationEmail;
import com.feljtech.istudybucket.dto.form.RegisterForm;
import com.feljtech.istudybucket.entity.User;
import com.feljtech.istudybucket.entity.VerificationToken;
import com.feljtech.istudybucket.repository.UserRepository;
import com.feljtech.istudybucket.repository.VerificationTokenRepository;
import com.feljtech.istudybucket.service.AuthService;
import com.feljtech.istudybucket.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@SuppressWarnings("FieldCanBeLocal")
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final String BASE_URL = "http://localhost:8080/";

    @Override
    @Transactional
    public void register(RegisterForm registerForm) {
        User newUser = User.builder() // build the new User object from the register form
                .username(registerForm.getUsername())
                .email(registerForm.getEmail())
                .password(this.encodePassword(registerForm.getPassword())) // encode password
                .creationDate(Instant.now())
                .userEnabled(Boolean.FALSE)
                .build();

        userRepository.save(newUser); // save user to database

        String verToken = this.generateVerificationToken(newUser);

        // first, generate the DefaultEmail object
        VerificationEmail verificationEmail = (VerificationEmail) DefaultEmail.builder()
                .message("Verify")
                .subject("iSB - Verify Email")
                .recipient(newUser.getEmail())
                .build();

        // add the verification url and recipient name to the email object
        verificationEmail.setVerificationUrl(BASE_URL.concat(verToken));
        verificationEmail.setRecipientName(newUser.getUsername());

        // send the email through MailService
        mailService.sendVerificationEmail(verificationEmail);
    }

    private String generateVerificationToken(User newUser) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder() // build the verification token object
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
