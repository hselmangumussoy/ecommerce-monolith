package com.hsgumussoy.javaodev2.impl;

import com.hsgumussoy.javaodev2.dto.BasketDto;
import com.hsgumussoy.javaodev2.dto.UserDto;
import com.hsgumussoy.javaodev2.entity.Basket;
import com.hsgumussoy.javaodev2.entity.BasketProduct;
import com.hsgumussoy.javaodev2.entity.User;
import com.hsgumussoy.javaodev2.exception.RecordNotFoundExceptions;
import com.hsgumussoy.javaodev2.mapper.BasketMapper;
import com.hsgumussoy.javaodev2.repository.BasketRepository;
import com.hsgumussoy.javaodev2.service.BasketProductService;
import com.hsgumussoy.javaodev2.service.BasketService;
import com.hsgumussoy.javaodev2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private BasketRepository repository;

    @Autowired
    private BasketMapper basketMapper;

    private static  int BASKET_STATUS_NONE = 0;
    private static int BASKET_STATUS_SALED = 1;
    @Override
    public BasketDto save(BasketDto dto) {

    }

    @Override
    public BasketDto get(String id) {
        return basketMapper.entityToDto(repository.findById(Long.parseLong(id))
                .orElseThrow(()-> new RecordNotFoundExceptions(4000,"Basket not found with id"+id)));
    }


    @Override
    public void delete(String id) {
        repository.deleteById(Long.parseLong(id));
    }

    @Override
    public BasketDto update(String id, BasketDto dto) {

    }

    @Override
    public List<BasketDto> getAll() {
        List<BasketDto> basketDtoList = new ArrayList<>();

        for(Basket basket: repository.findAll()){
            basketDtoList.add(basketMapper.entityToDto(basket));
        }
        return basketDtoList;

    }






}




