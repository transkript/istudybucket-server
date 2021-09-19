package com.feljtech.istudybucket.service;

import com.feljtech.istudybucket.dto.form.LoginForm;
import com.feljtech.istudybucket.dto.form.RegisterForm;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<?> registerAccount(RegisterForm registerForm);

    boolean verifyAccount(String verificationToken, String username);

    ResponseEntity<?> loginUser(LoginForm loginForm) throws Exception;
}
