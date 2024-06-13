package com.hsgumussoy.javaodev2.repository;

import com.hsgumussoy.javaodev2.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findById(Long id);

    Product findProductById(long id);

    Product deleteProductById(long id);
}
