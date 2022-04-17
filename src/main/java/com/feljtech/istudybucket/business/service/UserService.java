package com.feljtech.istudybucket.business.service;

import com.feljtech.istudybucket.api.dto.UserDto;
import com.feljtech.istudybucket.data.entity.User;

import java.util.List;

/**
 * UserService: interface for all User business logic
 */
public interface UserService {
    Long saveUser(UserDto userDto);


    User getUser(Long id);

    User getUser(String username);

    List<User> getAllUsers();
}
