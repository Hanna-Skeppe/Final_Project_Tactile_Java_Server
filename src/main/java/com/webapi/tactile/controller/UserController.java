package com.webapi.tactile.controller;
// hantera protected routes osv...

import com.webapi.tactile.models.UserVerification;
import com.webapi.tactile.service.UserServiceImpl;
import com.webapi.tactile.utils.Mappings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@Slf4j
@CrossOrigin(origins = Mappings.REACT_HOST)
@RestController
@RequestMapping(Mappings.USER)
public class UserController {

    // == fields ==
    UserServiceImpl userService;

    // == constructors ==
    public UserController(UserServiceImpl userService) {
        this.userService= userService;
    }

    // == public methods ==
    @PostMapping(Mappings.VERIFY_TOKEN)
    public UserVerification getUserDetails() {
        log.info("verify user");
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.verifyUser(email);
    }
}
