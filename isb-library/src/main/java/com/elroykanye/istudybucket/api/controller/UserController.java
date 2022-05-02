package com.elroykanye.istudybucket.api.controller;

import com.elroykanye.istudybucket.business.service.UserService;
import com.elroykanye.istudybucket.data.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kanye
 * Controller for the User entity: Handles all requests under the "/user/" endpoint
 */
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    /**
     * Autowired userService bean by Constructor DI into userController
     */
    private final UserService userService;

    @GetMapping(value = "/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
