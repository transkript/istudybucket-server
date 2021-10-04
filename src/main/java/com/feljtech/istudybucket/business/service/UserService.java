package com.feljtech.istudybucket.business.service;

import com.feljtech.istudybucket.data.entity.User;

import java.util.List;

/**
 * UserService: interface for all User business logic
 */
public interface UserService {

    List<User> getAllUsers();
}
