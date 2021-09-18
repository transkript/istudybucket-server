package com.feljtech.istudybucket.service.impl;

import com.feljtech.istudybucket.dto.email.VerificationEmail;
import com.feljtech.istudybucket.dto.form.LoginForm;
import com.feljtech.istudybucket.dto.form.RegisterForm;
import com.feljtech.istudybucket.dto.response.AuthResponse;
import com.feljtech.istudybucket.entity.User;
import com.feljtech.istudybucket.entity.VerificationToken;
import com.feljtech.istudybucket.enums.UserRole;
import com.feljtech.istudybucket.repository.UserRepository;
import com.feljtech.istudybucket.repository.VerificationTokenRepository;
import com.feljtech.istudybucket.service.AuthService;
import com.feljtech.istudybucket.service.MailService;
import com.feljtech.istudybucket.service.security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@SuppressWarnings("FieldCanBeLocal")
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    private final String BASE_URL = "http://localhost:8080/";

    @Override
    @Transactional
    public void registerAccount(RegisterForm registerForm) {
        User newUser = User.builder() // build the new User object from the register form
                .username(registerForm.getUsername())
                .email(registerForm.getEmail())
                .password(this.encodePassword(registerForm.getPassword())) // encode password
                .creationDate(Instant.now())
                .userVerified(Boolean.FALSE)
                .userRole(UserRole.USER)
                .build();

        userRepository.save(newUser); // save user to database

        String verToken = this.generateVerificationToken(newUser);

        // first, generate the DefaultEmail object
        VerificationEmail verificationEmail = VerificationEmail.builder()
                .message("verifiy")
                .subject("iSB-verification")
                .recipient(newUser.getEmail())
                .build();

        // add the verification url and recipient name to the email object
        verificationEmail.setVerificationUrl(BASE_URL.concat(verToken));
        verificationEmail.setRecipientName(newUser.getUsername());

        // send the email through MailService
        mailService.sendVerificationEmail(verificationEmail);
    }

    @Override
    @Transactional
    public boolean verifyAccount(String verificationTokenValue, String username) {
        AtomicBoolean accountValid = new AtomicBoolean(false);

        // extract optional verification token from the db
        Optional<VerificationToken> verificationTokenOpt = verificationTokenRepository.findByTokenValue(verificationTokenValue);

        verificationTokenOpt.ifPresent(verificationToken -> {
            // set the account valid value based on the verification
            accountValid.set(verificationToken.getUser().getUsername().equals(username));

            // get the Optional value for the user from the userRep object
            Optional<User> userToBeVerified = userRepository.findById(verificationToken.getUser().getUserId());

            // if user is present, perform the action declared,
            // pass call to update the user's verification
            userToBeVerified.ifPresent(user -> updateUserVerification(user, accountValid.get()));

            // delete the verification token if the operation was successful
            if (accountValid.get()) verificationTokenRepository.deleteById(verificationToken.getTokenId());
        });
        return accountValid.get();
    }

    @Override
    public AuthResponse loginUser(LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginForm.getUsername(),
                        loginForm.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwToken = jwtProvider.generateJwt(authentication);
        return new AuthResponse(jwToken, loginForm.getUsername());
    }

    // helper methods for this class
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

    private void updateUserVerification(User user, Boolean verified) {
        userRepository.deleteById(user.getUserId()); // delete from the db by that user id
        user.setUserVerified(verified); // update the value of user enabled
        userRepository.save(user); // saved the updated user
    }
}
