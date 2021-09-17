package com.feljtech.istudybucket.service;

import com.feljtech.istudybucket.dto.form.RegisterForm;

public interface AuthService {
    void registerAccount(RegisterForm registerForm);

    boolean verifyAccount(String verificationToken, String username);
}
