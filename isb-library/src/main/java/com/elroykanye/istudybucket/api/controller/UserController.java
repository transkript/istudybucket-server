package com.elroykanye.istudybucket.api.controller;

import com.elroykanye.istudybucket.api.dto.UserDto;
import com.elroykanye.istudybucket.business.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author kanye
 * Controller for the User entity: Handles all requests under the "/user/" endpoint
 */
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private final UserService userService;

    /**
     * Returns a List of all users
     * @return ResponseEntity with status 200 and a list of all users
     */
    @GetMapping(value = "")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Returns a user by id
     * @param id id of the user
     * @return ResponseEntity with status 200 and the user
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    /**
     * Returns a user by username
     * @param username username of the user
     * @return ResponseEntity with status 200 and the user
     */
    @GetMapping(value = "/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @PutMapping(value = "")
    public ResponseEntity<String> getUserByUsername(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(userDto));
    }
}
