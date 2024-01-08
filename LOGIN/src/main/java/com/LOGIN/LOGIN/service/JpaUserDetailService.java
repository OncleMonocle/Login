package com.LOGIN.LOGIN.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.LOGIN.LOGIN.model.UserDto;
import com.LOGIN.LOGIN.repository.UserRepository;

@Service
public class JpaUserDetailService implements UserDetailsService{

    private final UserRepository userRepository;

    
    public JpaUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return userRepository.findByUsername(username)
        .map(UserDto::new)
        .orElseThrow(()-> new UsernameNotFoundException("Hittade ingen användare"));
    }
    
} 
