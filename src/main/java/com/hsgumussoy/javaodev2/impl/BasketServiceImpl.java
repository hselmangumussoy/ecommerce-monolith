package com.hsgumussoy.javaodev2.impl;

import com.hsgumussoy.javaodev2.dto.BasketDto;
import com.hsgumussoy.javaodev2.dto.BasketProductDto;
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
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private BasketProductService basketProductService;

    private static  int BASKET_STATUS_NONE = 0;
    private static int BASKET_STATUS_SALED = 1;
    @Override
    public BasketDto save(BasketDto dto) {
        // Kullanıcıya ait açı bir sepet var mı kontrol et
        User user = userService.findUserById(dto.getUserId());
        Optional<Basket> existingBasket = repository.findByUserIdAndStatus(user.getId(),BASKET_STATUS_NONE);

        if(existingBasket.isPresent()){
            //mevcut sepeti döndür.
            return basketMapper.entityToDto(existingBasket.get());
        }else {
            // yeni sepet oluştur.
            Basket newBasket = basketMapper.dtoToEntity(dto);

            newBasket.setStatus(BASKET_STATUS_NONE);
            newBasket.setUser(user);

            Basket savedBasket = repository.save(newBasket);

            //sepete ürün ekle
            for(BasketProduct bp : newBasket.getBasketProductList()){
                bp.setBasket(savedBasket);
                basketProductService.save(bp);
            }

            return basketMapper.entityToDto(savedBasket);
        }
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
        // sepetin varlığını kontrol et
        Basket existingBasket = repository.findById(Long.parseLong(id))
                .orElseThrow(()-> new RecordNotFoundExceptions(4000, "Basket not found with id"+id));

        //sepet durumu kontrolü
        if(existingBasket.getStatus() == BASKET_STATUS_SALED){
            throw new IllegalArgumentException("Can not update a sold basket" );
        }

        //sepeti güncelle
        existingBasket = basketMapper.dtoToEntity(dto);
        existingBasket.setId(Long.parseLong(id));
        Basket updatedBasket = repository.save(existingBasket);

        //Sepetteki ürünleri güncelle
        for(BasketProduct bp: updatedBasket.getBasketProductList()){
            bp.setBasket(updatedBasket);
            basketProductService.save(bp);
        }
        return basketMapper.entityToDto(updatedBasket);
    }


    @Override
    public List<BasketDto> getAll() {
        List<BasketDto> basketDtoList = new ArrayList<>();

        for(Basket basket: repository.findAll()){
            basketDtoList.add(basketMapper.entityToDto(basket));
        }
        return basketDtoList;

    }


    public Basket findBasketById(Long basketId) {
        if(basketId == null){
            throw  new IllegalArgumentException("The given id must not be null");
        }
        return repository.findById(basketId).orElseThrow();
    }
}




