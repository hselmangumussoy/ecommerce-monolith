package com.hsgumussoy.javaodev2.repository;

import com.hsgumussoy.javaodev2.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BasketRepository extends JpaRepository<Basket, Long> {
    void deleteById(Long id);
    Basket findByUser_Id(Long id);
    Basket findByBasketId(Long id);
}
