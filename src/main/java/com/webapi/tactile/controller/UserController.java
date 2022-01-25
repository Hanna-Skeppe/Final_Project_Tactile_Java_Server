package com.webapi.tactile.controller;
// hantera protected routes osv...

import com.webapi.tactile.entities.AppUser;
import com.webapi.tactile.repository.AppUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/user")
public class UserController {
    // == fields ==
    private AppUserRepository userRepository;

    // == contructors ==
    public UserController(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // == public methods ==
    @PostMapping("/verify_token")
    public AppUser getUserDetails() {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(email).get();
    }
}
