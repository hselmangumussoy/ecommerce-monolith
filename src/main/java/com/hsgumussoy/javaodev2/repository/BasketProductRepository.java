package com.hsgumussoy.javaodev2.repository;

import com.hsgumussoy.javaodev2.entity.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketProductRepository extends JpaRepository<BasketProduct, Long> {
    Optional<BasketProduct> findByBasketIdAndProductId(Long basketId, Long productId);
}
