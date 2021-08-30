package com.feljtech.istudybucket.service.impl;

import com.feljtech.istudybucket.entity.User;
import com.feljtech.istudybucket.form.RegisterForm;
import com.feljtech.istudybucket.repository.UserRepository;
import com.feljtech.istudybucket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long registerUser(RegisterForm registerForm) {
        User user = User.builder()
                .username(registerForm.getUsername())
                .firstName(registerForm.getFirstName())
                .lastName(registerForm.getLastName()).build();
        return userRepository.save(user).getUserId();

    }
}
