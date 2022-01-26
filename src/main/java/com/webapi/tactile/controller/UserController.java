package com.webapi.tactile.controller;
// hantera protected routes osv...

import com.webapi.tactile.entities.AppUsersEntity;
import com.webapi.tactile.models.UserVerification;
import com.webapi.tactile.repository.AppUserRepository;
import com.webapi.tactile.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
public class UserController {
    // == fields ==
    //private AppUserRepository userRepository;
    UserServiceImpl userService;

    // == constructors ==
    public UserController(UserServiceImpl userService) {
        this.userService= userService;
    }

    // == public methods ==
    @PostMapping("/verify_token")
    public UserVerification getUserDetails() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.verifyUser(email);
    }
}
