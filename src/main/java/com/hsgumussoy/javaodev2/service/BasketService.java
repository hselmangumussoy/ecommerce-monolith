package com.hsgumussoy.javaodev2.service;

import com.hsgumussoy.javaodev2.dto.BasketDto;

public interface BasketService {
    public BasketDto save(BasketDto dto);
    public BasketDto get(String id);
    public void delete(String id);
    public BasketDto update(String id, BasketDto dto);
    //public List<Basket>getAll();
}
