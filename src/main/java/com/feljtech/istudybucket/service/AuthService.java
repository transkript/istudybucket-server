package com.feljtech.istudybucket.service;

import com.feljtech.istudybucket.dto.request.UserLoginRequest;
import com.feljtech.istudybucket.dto.request.UserRegisterRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> registerAccount(UserRegisterRequest userRegisterRequest);

    boolean verifyAccount(String verificationToken, String username);

    ResponseEntity<?> loginUser(UserLoginRequest userLoginRequest) throws Exception;
}
