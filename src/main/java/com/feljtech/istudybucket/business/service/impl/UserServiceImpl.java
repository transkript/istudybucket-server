package com.feljtech.istudybucket.business.service.impl;

import com.feljtech.istudybucket.api.dto.UserDto;
import com.feljtech.istudybucket.data.entity.User;
import com.feljtech.istudybucket.data.enums.UserRole;
import com.feljtech.istudybucket.data.repository.UserRepository;
import com.feljtech.istudybucket.business.service.UserService;
import com.feljtech.istudybucket.excetion.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long saveUser(UserDto userDto) {
        Long[] userId = new Long[1];
        Optional<User> userOptional = userRepository.findByUsernameOrEmail(userDto.getUsername(), userDto.getEmail());
        userOptional.ifPresentOrElse(
                user -> {
                    userId[0] = null;
                    throw new UserException.UserAlreadyExists(user.getUsername(), user.getEmail());
                },
                () -> {

                    User newUser = User.builder() // build the new User object from the register form
                            .username(userDto.getUsername())
                            .email(userDto.getEmail())
                            .password(userDto.getPassword()) // encode password
                            .createdDate(Instant.now())
                            .userVerified(Boolean.FALSE)
                            .userRole(UserRole.USER)
                            .build();

                    userId[0] = userRepository.save(newUser).getUserId(); // save user to database
                }
        );
        return userId[0];
    }

    @Override
    public User getUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserException.UserNotFound(id);
        }
    }

    @Override
    public User getUser(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserException.UserNotFound(username);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
