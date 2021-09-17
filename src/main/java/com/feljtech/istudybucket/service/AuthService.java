package com.feljtech.istudybucket.service;

import com.feljtech.istudybucket.dto.RegisterForm;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {
    void register(RegisterForm registerForm, HttpServletRequest request);
}
