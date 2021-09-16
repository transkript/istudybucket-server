package com.feljtech.istudybucket.service;

import com.feljtech.istudybucket.dto.form.RegisterForm;

/**
 * UserService: interface for all User business logic
 */
public interface UserService {
    Long registerUser(RegisterForm registerForm);
}
