package com.feljtech.istudybucket.controller;

import com.feljtech.istudybucket.dto.form.RegisterForm;
import com.feljtech.istudybucket.entity.User;
import com.feljtech.istudybucket.service.UserService;
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
