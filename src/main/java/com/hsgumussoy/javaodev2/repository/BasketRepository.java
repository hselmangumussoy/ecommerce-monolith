package com.hsgumussoy.javaodev2.repository;

import com.hsgumussoy.javaodev2.dto.BasketDto;
import com.hsgumussoy.javaodev2.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BasketRepository extends JpaRepository<Basket, Long> {

    Optional<Basket> findByUserIdAndStatus(Long id, int basketStatusNone);
}
