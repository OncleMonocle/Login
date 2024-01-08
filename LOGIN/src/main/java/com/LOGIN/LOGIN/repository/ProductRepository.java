package com.LOGIN.LOGIN.repository;

import org.springframework.data.repository.CrudRepository;

import com.LOGIN.LOGIN.model.Product;

public interface ProductRepository extends CrudRepository <Product, Integer> {
    
}
