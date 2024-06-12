package com.hsgumussoy.javaodev2.service.impl;

import com.hsgumussoy.javaodev2.dto.BasketDto;
import com.hsgumussoy.javaodev2.entity.Basket;
import com.hsgumussoy.javaodev2.repository.BasketRepository;
import com.hsgumussoy.javaodev2.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private BasketRepository repository;

    public BasketDto save(BasketDto dto) {
        return toDto(repository.save(toEntity(dto)));
    }

    public BasketDto get(String id) {
        return toDto(repository.findByBasketId(Long.parseLong(id)));
    }

    public void delete(String id) {
        repository.deleteById(Long.parseLong(id));
    }

    public BasketDto update(String id, BasketDto dto) {
        Basket existBasket = repository.findByBasketId(Long.parseLong(id));
        if (existBasket != null) {
            existBasket.setUser(dto.getUser());
        } else {
            return null;
        }
        return toDto(repository.save(existBasket));
    }

    private Basket toEntity(BasketDto dto) {
        Basket basket = new Basket();
        basket.setId(dto.getId());
        basket.setUser(dto.getUser());
        return basket;
    }

    private BasketDto toDto(Basket basket) {
        BasketDto dto = new BasketDto();
        dto.setId(basket.getId());
        dto.setUser(basket.getUser());
        return dto;
    }
}
