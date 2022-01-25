package com.webapi.tactile.config;

import com.webapi.tactile.repository.AppUserRepository;
import com.webapi.tactile.security.JWTFilter;
import com.webapi.tactile.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // fields
    private AppUserRepository userRepository;
    private JWTFilter filter;
    private MyUserDetailsService uds;

    // constructor
    @Autowired
    public SecurityConfig(
            AppUserRepository userRepository,
            JWTFilter filter,
            MyUserDetailsService uds) {
        this.userRepository = userRepository;
        this.filter = filter;
        this.uds = uds;
    }

    // Beans
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //public methods:
    public SecurityConfig(
            boolean disableDefaults,
            AppUserRepository userRepository,
            JWTFilter filter,
            MyUserDetailsService uds) {
        super(disableDefaults);
        this.userRepository = userRepository;
        this.filter = filter;
        this.uds = uds;
    }

    // == protected methods ==
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic().disable()
                .cors()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/user/**").hasRole("USER")
                .and()
                .userDetailsService(uds)
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                )
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
    }
}
