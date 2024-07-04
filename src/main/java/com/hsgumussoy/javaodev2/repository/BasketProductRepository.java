package com.hsgumussoy.javaodev2.repository;

import com.hsgumussoy.javaodev2.entity.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {
}
