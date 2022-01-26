package com.webapi.tactile.repository;


import com.webapi.tactile.entities.AppUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUsersEntity, Integer> {
    public Optional<AppUsersEntity> findByEmail(String email);
}
