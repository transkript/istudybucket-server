package com.feljtech.istudybucket.service;

import com.feljtech.istudybucket.dto.form.RegisterForm;
import com.feljtech.istudybucket.entity.User;

import java.util.List;

/**
 * UserService: interface for all User business logic
 */
public interface UserService {

    List<User> getAllUsers();
}
