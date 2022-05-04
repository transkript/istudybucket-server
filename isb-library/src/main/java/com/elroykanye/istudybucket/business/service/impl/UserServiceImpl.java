package com.elroykanye.istudybucket.business.service.impl;

import com.elroykanye.istudybucket.api.dto.UserDto;
import com.elroykanye.istudybucket.business.mapper.UserMapper;
import com.elroykanye.istudybucket.business.service.UserService;
import com.elroykanye.istudybucket.data.entity.User;
import com.elroykanye.istudybucket.data.enums.UserRole;
import com.elroykanye.istudybucket.data.repository.UserRepository;
import com.elroykanye.istudybucket.excetion.EntityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public Long saveUser(UserDto userDto) {
        log.info("Saving user: {}", userDto);
        Long[] userId = new Long[1];
        Optional<User> userOptional = userRepository.findByUsernameOrEmail(userDto.getUsername(), userDto.getEmail());
        userOptional.ifPresentOrElse(
                user -> {
                    userId[0] = null;
                    log.error("User already exists: {}", user);
                    throw new EntityException.EntityAlreadyExistsException("user", user.getUsername(), user.getEmail());
                },
                () -> {
                    log.info("User does not exist, creating new user");
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
        log.info("Getting user with userid: {}", id);
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            log.info("User with userid {} found", id);
            return userOptional.get();
        } else {
            log.warn("User with userid {} not found", id);
            throw new EntityException.EntityNotFoundException("user", id);
        }
    }

    @Override
    public User getUser(String username) {
        log.info("Getting user with username: {}", username);
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isPresent()) {
            log.info("User with username {} found", username);
            return userOptional.get();
        } else {
            log.warn("User with username {} not found", username);
            throw new EntityException.EntityNotFoundException("user", username);
        }
    }

    @Override
    public UserDto getUserById(Long id) {
        log.info("Getting user with id: {}", id);
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            log.info("User found");
            return userMapper.mapUserToDto(userOptional.get());
        }
        log.warn("User not found");
        throw new EntityException.EntityNotFoundException("user", id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Getting all users");
        return userRepository.findAll().stream().map(userMapper::mapUserToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserByUsername(String username) {
        log.info("Getting user with username: {}", username);
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(userOptional.isPresent()) {
            log.info("User found");
            return userMapper.mapUserToDto(userOptional.get());
        }
        log.warn("User not found");
        throw new EntityException.EntityNotFoundException("user", username);
    }

    @Override
    public String updateUser(UserDto userDto) {
        log.info("Updating user with username: {}", userDto.getUsername());
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());
        if(userOptional.isPresent()) {
            log.info("User found");
            User userUpdated = userMapper.mapDtoToUser(userDto);

            User user = userOptional.get();
            user.setUsername(userUpdated.getUsername());
            user.setEmail(userUpdated.getEmail());
            user.setFirstName(userUpdated.getFirstName());
            user.setLastName(userUpdated.getLastName());
            user.setPhoneNumber(userUpdated.getPhoneNumber());
            user.setDob(userUpdated.getDob());
            user.setGender(userUpdated.getGender());

            userRepository.save(user);

            return "User updated successfully";
        }
        log.warn("User not found");
        throw new EntityException.EntityNotFoundException("user", userDto.getUsername());
    }
}
