package com.LOGIN.LOGIN.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.LOGIN.LOGIN.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{

    Optional <User> findByUsername(String username);
    
}