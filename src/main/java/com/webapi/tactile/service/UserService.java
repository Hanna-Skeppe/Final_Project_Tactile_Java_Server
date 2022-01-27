package com.webapi.tactile.service;

import com.webapi.tactile.models.UserRegistration;
import com.webapi.tactile.models.UserVerification;

import java.util.Map;

public interface UserService {
        UserVerification verifyUser(String email);
        Map<String, Object> createUser(UserRegistration user);
}
