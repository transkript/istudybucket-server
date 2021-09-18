package com.feljtech.istudybucket.service;

import com.feljtech.istudybucket.dto.form.LoginForm;
import com.feljtech.istudybucket.dto.form.RegisterForm;
import com.feljtech.istudybucket.dto.response.AuthResponse;

public interface AuthService {
    void registerAccount(RegisterForm registerForm);

    boolean verifyAccount(String verificationToken, String username);

    AuthResponse loginUser(LoginForm loginForm);

}
