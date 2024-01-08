package com.LOGIN.LOGIN.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.LOGIN.LOGIN.service.JpaUserDetailService;

@Configuration
public class SecurityConfig {

    private final JpaUserDetailService jpaUserDetailService;

    
    public SecurityConfig(JpaUserDetailService jpaUserDetailService) {
        this.jpaUserDetailService = jpaUserDetailService;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
        .anyRequest().authenticated()
        )
        .userDetailsService(jpaUserDetailService)
        .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder () {
        return NoOpPasswordEncoder.getInstance();
    }
}
