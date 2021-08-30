package com.feljtech.istudybucket.controller;

import com.feljtech.istudybucket.entity.User;
import com.feljtech.istudybucket.form.RegisterForm;
import com.feljtech.istudybucket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "auth")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "register")
    public Long register(@RequestBody RegisterForm registerForm) {
        return userService.registerUser(registerForm);
    }
}
