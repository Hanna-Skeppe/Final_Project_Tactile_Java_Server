package com.webapi.tactile.controller;
// registrering och login för användare

import com.webapi.tactile.entities.AppUser;
import com.webapi.tactile.models.LoginCredentials;
import com.webapi.tactile.repository.AppUserRepository;
import com.webapi.tactile.security.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@Slf4j // för logging, se vad vi får ut
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/auth")
public class AuthController {
    // == fields ==
    private AppUserRepository userRepository;
    private JWTUtil jwtUtil;
    private AuthenticationManager authManager;
    private PasswordEncoder passwordEncoder;

    // == contructors ==
    @Autowired
    public AuthController(
            AppUserRepository userRepository,
            JWTUtil jwtUtil,
            AuthenticationManager authManager,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
    }

    // == public methods ==
    @PostMapping("/register")
    public Map<String, Object> registerHandler(@RequestBody AppUser user){
        String encodedPass = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPass);
        user = userRepository.save(user);
        log.info("user= {}", user);
        String token = jwtUtil.generateToken(user.getEmail());
        log.info("user= {}", token);
        return Collections.singletonMap("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody LoginCredentials body){
        try {
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword());

            authManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(body.getEmail());

            return Collections.singletonMap("token", token);
        }catch (AuthenticationException authExc){
            throw new RuntimeException("Invalid Login Credentials");
        }
    }

    @GetMapping("/ping")
    public String pingString() {
        return "Hello from Spring Server";
    }


}
