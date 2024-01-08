package com.LOGIN.LOGIN.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

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
        .requestMatchers("/myOrders").authenticated()
        .anyRequest().permitAll()
        )
        .userDetailsService(jpaUserDetailService)
        .formLogin(Customizer.withDefaults())
        .exceptionHandling().accessDeniedHandler(accessDeniedHandler());

        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
    return new CustomAccessDeniedHandler();
}

}