package com.elroykanye.istudybucket.business.service;

import com.elroykanye.istudybucket.api.dto.UserDto;
import com.elroykanye.istudybucket.data.entity.User;

import java.util.List;

/**
 * UserService: interface for all User business logic
 */
public interface UserService {
    Long saveUser(UserDto userDto);

    User getUser(Long id);
    User getUser(String username);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto getUserByUsername(String username);

    String updateUser(UserDto userDto);
}
