package com.elroykanye.istudybucket.business.service.impl;

import com.elroykanye.istudybucket.api.dto.UserDto;
import com.elroykanye.istudybucket.api.dto.email.VerificationEmail;
import com.elroykanye.istudybucket.api.dto.request.LoginRequest;
import com.elroykanye.istudybucket.api.dto.request.RegisterRequest;
import com.elroykanye.istudybucket.api.dto.response.LoginResponse;
import com.elroykanye.istudybucket.api.dto.response.LogoutResponse;
import com.elroykanye.istudybucket.api.dto.response.RegisterResponse;
import com.elroykanye.istudybucket.business.service.AuthService;
import com.elroykanye.istudybucket.business.service.MailService;
import com.elroykanye.istudybucket.business.service.RefreshTokenService;
import com.elroykanye.istudybucket.business.service.UserService;
import com.elroykanye.istudybucket.config.jwt.JwtRefreshTokenRequest;
import com.elroykanye.istudybucket.config.jwt.JwtTokenUtil;
import com.elroykanye.istudybucket.data.entity.User;
import com.elroykanye.istudybucket.data.entity.VerificationToken;
import com.elroykanye.istudybucket.data.repository.UserRepository;
import com.elroykanye.istudybucket.data.repository.VerificationTokenRepository;
import com.elroykanye.istudybucket.excetion.AuthException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@SuppressWarnings("FieldCanBeLocal")
@Service
@AllArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    private final String BASE_URL = "http://localhost:8080/";
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;

    /**
     * Implementation for user Registration
     * Saves the non-existing user to the database, and sends a verification email to the user.
     * @param registerRequest : request body for registration
     * @return response entity based on success
     */
    @Override
    @Transactional
    public RegisterResponse registerAccount(RegisterRequest registerRequest) {
        // log the info with request body
        log.info("Registering user {}", registerRequest);
        UserDto userDto = UserDto.builder().email(registerRequest.getEmail())
                .username(registerRequest.getUsername()).password(this.encodePassword(registerRequest.getPassword()))
                .build();

        Long uid = userService.saveUser(userDto);

        String verificationToken = this.generateVerificationToken(userDto);

        // first, generate the DefaultEmail object
        VerificationEmail verificationEmail = VerificationEmail.builder()
                .message("Verify").subject("iSB-verification").recipient(userDto.getEmail())
                .build();

        // add the verification url and recipient name to the email object
        verificationEmail.setVerificationUrl(BASE_URL.concat(verificationToken));
        verificationEmail.setRecipientName(userDto.getUsername());

        // send the email through MailService
        mailService.sendVerificationEmail(verificationEmail);

        return RegisterResponse.builder()
                .userId(uid)
                .message("Registration successful").build();
    }

    /**
     * implementation for user verification
     * @param verificationTokenValue: token value
     * @param username: username concerned
     * @return logic state of user verification
     */
    @Override
    @Transactional
    public boolean verifyAccount(String verificationTokenValue, String username) {
        log.info("Verifying user {}", username);

        AtomicBoolean verTokenValid = new AtomicBoolean(false);

        verificationTokenRepository.findByTokenValue(verificationTokenValue).ifPresentOrElse((verificationToken) -> {
            log.info("Verification token found for user {}", username);

            // set the account valid value based on the verification
            verTokenValid.set(verificationToken.getUsername().equals(username));

            // get the Optional value for the user from the userRep object
            User userToBeVerified = userService.getUserEntity(verificationToken.getUsername());

            // if user is present, perform the action declared,
            // pass call to update the user's verification
            boolean userUpdated = false;
            if(verTokenValid.get()) {
                userUpdated = updateUserVerification(userToBeVerified, verTokenValid.get());
            }
            // delete the verification token if the operation was successful
            if(userUpdated) verificationTokenRepository.deleteById(verificationToken.getTokenId());
        }, () -> {
            log.error("Verification token not found");

            // set the account valid value to false
            throw new AuthException.UserVerificationException(username);
        });

        return verTokenValid.get();
    }

    /**
     * implementation for user authentication
     * @param loginRequest : request body for this method
     * @return response entity containing status and jwtResponse body
     */
    public LoginResponse loginUser(LoginRequest loginRequest) {
        log.info("Authenticating user {}", loginRequest.getUsername());

        authenticateUser(loginRequest);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        final String jwtToken = jwtTokenUtil.generateToken(userDetails);
        final String refreshToken = refreshTokenService.generateRefreshToken().getRefreshToken();
        final Instant jwtExpiration = Instant.now().plusMillis(jwtTokenUtil.getExpirationTimeInMillis(jwtToken));

        return LoginResponse.builder().jwtToken(jwtToken).refreshToken(refreshToken)
                .expiresAt(jwtExpiration).username(userDetails.getUsername()).build();
    }
    
    @Override
    public LoginResponse refreshToken(JwtRefreshTokenRequest jwtRefreshTokenRequest) {
        log.info("Refreshing token for user {}", jwtRefreshTokenRequest.getUsername());

        refreshTokenService.validateRefreshToken(jwtRefreshTokenRequest.getRefreshToken());

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRefreshTokenRequest.getUsername());

        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        String refreshToken = jwtRefreshTokenRequest.getRefreshToken();
        Instant jwtExpiration = Instant.now().plusMillis(jwtTokenUtil.getExpirationTimeInMillis(jwtToken));
        String username = jwtRefreshTokenRequest.getUsername();

        return LoginResponse.builder().jwtToken(jwtToken).refreshToken(refreshToken)
                .expiresAt(jwtExpiration).username(username).build();
    }

    @Override
    public LogoutResponse logoutUser(JwtRefreshTokenRequest jwtRefreshTokenRequest) {
        log.info("Logging out user {} and deleting refresh token", jwtRefreshTokenRequest.getUsername());
        refreshTokenService.deleteRefreshToken(jwtRefreshTokenRequest.getRefreshToken());
        return LogoutResponse.builder().username(jwtRefreshTokenRequest.getUsername()).message("Log out successful").build();
    }


    /* ************* helper methods for this class ************* */
    private void authenticateUser(LoginRequest loginRequest) {
        String requestUsername = loginRequest.getUsername();
        String requestPassword = loginRequest.getPassword();


        log.info("Authenticating user {}", requestUsername);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestUsername, requestPassword)
            );
            log.info("User {} authenticated successfully", requestUsername);
        } catch (AuthenticationException e) {
            log.info("Authentication failed for user {}", requestUsername);
            log.error(e.toString());
            throw new AuthException.LoginFailedException();
        }
    }

    private String generateVerificationToken(UserDto newUser) {
        log.info("Generating verification token for user {}", newUser.getUsername());

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder() // build the verification token object
                .tokenValue(token)
                .username(newUser.getUsername())
                .email(newUser.getEmail())
                .build();
        verificationTokenRepository.save(verificationToken); // save to the table
        return token;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private boolean updateUserVerification(User user, Boolean verified) {
        log.info("Updating user {} verification status to {}", user.getUsername(), verified);

        boolean verSuccess = false;
        try {
            user.setUserVerified(verified); // update the value of user enabled
            userRepository.save(user); // saved the updated user
            verSuccess = true;
        } catch (Exception e) {
            log.error("User verification update failed");
        }
        return verSuccess;
    }
    /* ************* helper methods for this class ************* */
}
