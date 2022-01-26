package com.webapi.tactile.service;

import com.webapi.tactile.entities.AppUsersEntity;
import com.webapi.tactile.models.UserVerification;

public interface UserService {
        UserVerification verifyUser(String email);
}
