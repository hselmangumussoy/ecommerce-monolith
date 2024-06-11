package com.hsgumussoy.javaodev2.repository;

import com.hsgumussoy.javaodev2.entity.Product;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(Long id);
}
