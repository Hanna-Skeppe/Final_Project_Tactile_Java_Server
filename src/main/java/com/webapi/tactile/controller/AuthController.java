package com.webapi.tactile.controller;
// registrering och login för användare

import com.webapi.tactile.models.LoginCredentials;
import com.webapi.tactile.models.UserRegistration;
import com.webapi.tactile.security.JWTUtil;
import com.webapi.tactile.service.UserService;
import com.webapi.tactile.utils.Mappings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j // för logging, se vad vi får ut
@RestController
@CrossOrigin(origins = Mappings.REACT_HOST) //Put mappings instead of magic strings.
@RequestMapping(Mappings.AUTH)
public class AuthController {

    // == fields ==
    private JWTUtil jwtUtil;
    private AuthenticationManager authManager;
    private UserService userService;

    // == constructors ==
    @Autowired
    public AuthController(
            JWTUtil jwtUtil,
            AuthenticationManager authManager,
            UserService userService) {
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
        this.userService = userService;
    }

    // == public methods ==
    @PostMapping(Mappings.REGISTER)
    public Map<String, Object> registerHandler(@Valid @RequestBody UserRegistration user){ //Added @Valid, moved logic to userService, change from AppUsers Entity to Model
        return userService.createUser(user);
    }

    @PostMapping(Mappings.LOGIN)
    public Map<String, Object> loginHandler(@Valid @RequestBody LoginCredentials body){ //Added @Valid
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

            authManager.authenticate(authInputToken);
            String token = jwtUtil.generateToken(body.getEmail());
            return Collections.singletonMap("token", token);

        } catch (AuthenticationException authExc) {
            throw new RuntimeException("Invalid Login Credentials");
        }
    }

    @GetMapping(Mappings.PING)
    public String pingString() {
        return "Hello from Spring Server";
    }

    //Added below will catch all validation errors, return bad_request with the errors.
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
