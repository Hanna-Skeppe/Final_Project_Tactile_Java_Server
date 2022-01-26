package com.webapi.tactile.security;

import com.webapi.tactile.entities.AppUsersEntity;
import com.webapi.tactile.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService{

    // == fields ==
    private AppUserRepository userRepository;

    // == constructors ==
    @Autowired
    public MyUserDetailsService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // == public methods ==
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUsersEntity> userRes = userRepository.findByEmail(email);
        if(userRes.isEmpty())
            throw new UsernameNotFoundException("Could not findUser with email = " + email);
        AppUsersEntity user = userRes.get();
        return new org.springframework.security.core.userdetails.User(
                email,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }


}
