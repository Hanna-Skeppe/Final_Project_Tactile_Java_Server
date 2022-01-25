package com.webapi.tactile.repository;

import com.webapi.tactile.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    public Optional<AppUser> findByEmail(String email);
}
