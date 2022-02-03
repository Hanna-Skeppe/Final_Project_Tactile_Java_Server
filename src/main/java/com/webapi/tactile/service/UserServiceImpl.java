package com.webapi.tactile.service;

import com.webapi.tactile.entities.AppUsersEntity;
import com.webapi.tactile.models.UserRegistration;
import com.webapi.tactile.models.UserVerification;
import com.webapi.tactile.repository.AppUserRepository;
import com.webapi.tactile.security.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    // == fields ==
    private AppUserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JWTUtil jwtUtil;


    // == constructors ==
    @Autowired
    public UserServiceImpl(AppUserRepository userRepository, PasswordEncoder passwordEncoder, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // == public methods ==
    @Override
    public UserVerification verifyUser(String email) {
        String dbEmail = userRepository.findByEmail(email).get().getEmail();
        UserVerification user = new UserVerification(dbEmail);
        return user;
    }

    @Override
    public Map<String, Object> createUser(UserRegistration userInput) {
        String encodedPass = passwordEncoder.encode(userInput.getPassword());
        userInput.setPassword(encodedPass);

        //Mapping from Data object to Entity object
        AppUsersEntity user = new AppUsersEntity();
        user.setEmail(userInput.getEmail());
        user.setPassword(userInput.getPassword());
        user.setFirstName(userInput.getFirstName());
        user.setLastName(userInput.getLastName());
        user.setStreetName(userInput.getStreetName());
        user.setCity(userInput.getCity());
        user.setZipcode(userInput.getZipcode());
        user.setCountry(userInput.getCountry());
        user.setPhoneNumber(userInput.getPhoneNumber());
        user.setNewsLetter(userInput.getNewsLetter());

        user = userRepository.save(user);
        String token = jwtUtil.generateToken(user.getEmail());

        return Collections.singletonMap("token", token);
    }
}
