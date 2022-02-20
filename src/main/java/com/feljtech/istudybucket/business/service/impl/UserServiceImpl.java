package com.feljtech.istudybucket.business.service.impl;

import com.feljtech.istudybucket.data.entity.User;
import com.feljtech.istudybucket.data.repository.UserRepository;
import com.feljtech.istudybucket.business.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
