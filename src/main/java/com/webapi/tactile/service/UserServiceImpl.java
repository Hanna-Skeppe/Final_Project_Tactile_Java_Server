package com.webapi.tactile.service;

import com.webapi.tactile.models.UserVerification;
import com.webapi.tactile.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    AppUserRepository userRepository;

    @Autowired
    public UserServiceImpl(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserVerification verifyUser(String email) {
        String dbEmail = userRepository.findByEmail(email).get().getEmail();
        UserVerification user = new UserVerification(dbEmail);
        return user;
    }
}
