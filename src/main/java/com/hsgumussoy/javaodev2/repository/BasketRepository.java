package com.hsgumussoy.javaodev2.repository;

import com.hsgumussoy.javaodev2.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {

    Basket findUserByBasketId(int i);

    Basket deleteBasketById(int i);
}
