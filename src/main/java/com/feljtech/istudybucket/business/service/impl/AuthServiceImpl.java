package com.feljtech.istudybucket.business.service.impl;

import com.feljtech.istudybucket.api.dto.email.VerificationEmail;
import com.feljtech.istudybucket.api.dto.request.LoginRequest;
import com.feljtech.istudybucket.api.dto.request.RegisterRequest;
import com.feljtech.istudybucket.business.service.MailService;
import com.feljtech.istudybucket.excetion.AuthException;
import com.feljtech.istudybucket.data.entity.User;
import com.feljtech.istudybucket.data.entity.VerificationToken;
import com.feljtech.istudybucket.data.enums.UserRole;
import com.feljtech.istudybucket.data.repository.UserRepository;
import com.feljtech.istudybucket.data.repository.VerificationTokenRepository;
import com.feljtech.istudybucket.config.jwt.JwtRefreshTokenRequest;
import com.feljtech.istudybucket.config.jwt.JwtResponse;
import com.feljtech.istudybucket.config.jwt.JwtTokenUtil;
import com.feljtech.istudybucket.business.service.AuthService;
import com.feljtech.istudybucket.business.service.RefreshTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
@Slf4j
public class AuthServiceImpl implements AuthService {
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
     * implementation for user registeration
     * @param registerRequest : request body for registration
     * @return response entity based on success
     */
    @Override
    @Transactional
    public ResponseEntity<?> registerAccount(RegisterRequest registerRequest) {
        AtomicBoolean userAlreadyExists = new AtomicBoolean(false);
        String newUsername = registerRequest.getUsername();
        String newEmail = registerRequest.getEmail();

        // query the database to find out if the user already exists
        Optional<User> userOptional = userRepository.findByUsernameOrEmail(newUsername, newEmail);
        userOptional.ifPresentOrElse(
                user -> userAlreadyExists.set(true),
                () -> {
                    userAlreadyExists.set(false);

                    User newUser = User.builder() // build the new User object from the register form
                            .username(registerRequest.getUsername())
                            .email(registerRequest.getEmail())
                            .password(this.encodePassword(registerRequest.getPassword())) // encode password
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
        );
        return userAlreadyExists.get() ?
                new ResponseEntity<>("User already exists", HttpStatus.CONFLICT):
                new ResponseEntity<>("Registration successful", HttpStatus.OK);
    }

    /**
     * implementation for user verification
     * @param verificationTokenValue: token value
     * @param username: username concerned
     * @return: logic state of user verification
     */
    @Override
    @Transactional
    public boolean verifyAccount(String verificationTokenValue, String username) {
        AtomicBoolean verTokenValid = new AtomicBoolean(false);

        // extract optional verification token from the db
        Optional<VerificationToken> verificationTokenOpt = verificationTokenRepository.findByTokenValue(verificationTokenValue);

        verificationTokenOpt.ifPresent(verificationToken -> {
            // set the account valid value based on the verification
            verTokenValid.set(verificationToken.getUsername().equals(username));

            // get the Optional value for the user from the userRep object
            Optional<User> userToBeVerified = userRepository.findByUsername(verificationToken.getUsername());

            // if user is present, perform the action declared,
            // pass call to update the user's verification
            userToBeVerified.ifPresent(user -> {
                boolean userUpdated = false;
                if(verTokenValid.get()) {
                    userUpdated = updateUserVerification(user, verTokenValid.get());
                }
                // delete the verification token if the operation was successful

                if(userUpdated) verificationTokenRepository.deleteById(verificationToken.getTokenId());
            });
        });
        return verTokenValid.get();
    }

    /**
     * implementation for user authentication
     * @param loginRequest: request body for this method
     * @return: response entity containing status and jwtResponse body
     * @throws Exception: in case authentication is unsuccessful
     */
    public ResponseEntity<?> loginUser(LoginRequest loginRequest) throws Exception {

        authenticateUser(loginRequest);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

        final String jwtToken = jwtTokenUtil.generateToken(userDetails);
        final String refreshToken = refreshTokenService.generateRefreshToken().getRefreshToken();
        final Instant jwtExpiration = Instant.now().plusMillis(jwtTokenUtil.getExpirationTimeInMillis(jwtToken));

        return new ResponseEntity<>(
                JwtResponse.builder()
                        .jwtToken(jwtToken)
                        .refreshToken(refreshToken)
                        .username(userDetails.getUsername())
                        .expiresAt(jwtExpiration)
                        .build(),
                HttpStatus.OK
        );
    }
    
    @Override
    public ResponseEntity<?> refreshToken(JwtRefreshTokenRequest jwtRefreshTokenRequest) {
        refreshTokenService.validateRefreshToken(jwtRefreshTokenRequest.getRefreshToken());

        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRefreshTokenRequest.getUsername());

        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        String jwtRefreshToken = jwtRefreshTokenRequest.getRefreshToken();
        Instant jwtExpiration = Instant.now().plusMillis(jwtTokenUtil.getExpirationTimeInMillis(jwtToken));
        String username = jwtRefreshTokenRequest.getUsername();

        return new ResponseEntity<>(
                JwtResponse.builder()
                        .jwtToken(jwtToken)
                        .refreshToken(jwtRefreshToken)
                        .expiresAt(jwtExpiration)
                        .username(username)
                        .build(),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<String> logoutUser(JwtRefreshTokenRequest jwtRefreshTokenRequest) {
        try {
            refreshTokenService.deleteRefreshToken(jwtRefreshTokenRequest.getRefreshToken());
            return new ResponseEntity<>(
                    jwtRefreshTokenRequest.getUsername().concat(" has logged out!"),
                    HttpStatus.OK
            );
        } catch (Exception exception) {
            return new ResponseEntity<>("Invalid token", HttpStatus.FORBIDDEN);
        }
    }


    /* ************* helper methods for this class ************* */
    private void authenticateUser(LoginRequest loginRequest) throws Exception {
        String requestUsername = loginRequest.getUsername();
        String requestPassword = loginRequest.getPassword();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestUsername, requestPassword)
            );
        } catch (AuthenticationException e) {
            log.error(e.toString());
            //throw new IstudybucketException("Authentication failed");
            throw new AuthException.LoginFailedException();
        }
    }

    private String generateVerificationToken(User newUser) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = VerificationToken.builder() // build the verification token object
                .tokenValue(token)
                .username(newUser.getUsername())
                .userEmail(newUser.getEmail())
                .build();
        verificationTokenRepository.save(verificationToken); // save to the table
        return token;
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    private boolean updateUserVerification(User user, Boolean verified) {
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
