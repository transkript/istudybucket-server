package com.elroykanye.istudybucket.api.controller;

import com.elroykanye.istudybucket.api.dto.request.LoginRequest;
import com.elroykanye.istudybucket.api.dto.request.RegisterRequest;
import com.elroykanye.istudybucket.api.dto.response.RegisterResponse;
import com.elroykanye.istudybucket.business.service.AuthService;
import com.elroykanye.istudybucket.config.jwt.JwtRefreshTokenRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author Elroy Kanye
 *
 * Modified on: 12-09-2021
 * Modified by: Elroy Kanye
 *
 * Description: Handles all user authentication services
 */

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    private final AuthService authService;

    /**
     * registers a user from a RegisterForm object, consumed from JSON.
     * typically, control is passed to the userService bean.
     * @param registerRequest: the form as an object - spring auto converts based on input names.
     * @return http status of the process
     */
    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.registerAccount(registerRequest));
    }

    /**
     * Activates the user verification process using the sent token via email
     * @param verificationToken: the string token received by the user
     * @param username: username of the associated user
     * @return response entity object describing success of operation
     */
    @GetMapping(value = "/verify/{username}")
    public ResponseEntity<String> verify(@RequestParam(value = "verToken") String verificationToken, @PathVariable String username) {
        return authService.verifyAccount(verificationToken, username) ?
                new ResponseEntity<>("User verification successful", HttpStatus.ACCEPTED):
                new ResponseEntity<>("User verification unsuccessful", HttpStatus.BAD_REQUEST);
    }

    /**
     * activate user authentication process
     * @param loginRequest: request body of the endpoint
     * @return: response containing jwtResponse body
     * @throws Exception: in case auth failed
     */
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        return authService.loginUser(loginRequest);
    }

    @PostMapping("/refresh/token")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody JwtRefreshTokenRequest jwtRefreshTokenRequest) {
        return authService.refreshToken(jwtRefreshTokenRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody JwtRefreshTokenRequest jwtRefreshTokenRequest) {
        return authService.logoutUser(jwtRefreshTokenRequest);
    }

}
