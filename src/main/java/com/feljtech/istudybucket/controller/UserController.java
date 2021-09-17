package com.feljtech.istudybucket.controller;

import com.feljtech.istudybucket.dto.form.RegisterForm;
import com.feljtech.istudybucket.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kanye
 * Controller for the User entity: Handles all requests under the "/user/" endpoint
 */
@AllArgsConstructor
@RestController
@RequestMapping(value = "user")
public class UserController {
    /**
     * Autowired userService bean by Constructor DI into userController
     */
    private final UserService userService;


}
