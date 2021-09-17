package com.feljtech.istudybucket.service;

import com.feljtech.istudybucket.dto.form.RegisterForm;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    void register(RegisterForm registerForm, HttpServletRequest request);
}
