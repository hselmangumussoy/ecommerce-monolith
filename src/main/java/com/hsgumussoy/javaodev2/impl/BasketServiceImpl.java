package com.hsgumussoy.javaodev2.impl;

import com.hsgumussoy.javaodev2.dto.BasketDto;
import com.hsgumussoy.javaodev2.dto.UserDto;
import com.hsgumussoy.javaodev2.entity.Basket;
import com.hsgumussoy.javaodev2.entity.BasketProduct;
import com.hsgumussoy.javaodev2.entity.User;
import com.hsgumussoy.javaodev2.repository.BasketRepository;
import com.hsgumussoy.javaodev2.service.BasketProductService;
import com.hsgumussoy.javaodev2.service.BasketService;
import com.hsgumussoy.javaodev2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private BasketRepository repository;

    @Autowired
    private BasketProductService basketProductService;

    private final int BASKET_STATUS_NONE = 0;
    private final int BASKET_STATUS_SALED = 1;
    @Override
    public BasketDto save(BasketDto dto) {
        Optional<Basket> basket =repository.findByUser_UserIdAndStatusEquals(dto.getUser().setId(), BASKET_STATUS_NONE);
        if (basket.isPresent()){
            return haveBasketAddNew(basket.get(),basketToDto())
        }else {
            return nullBasketAddPrdoduct(basketToDto());
        }
    }

    private BasketDto haveBasketAddNew(Basket basket, BasketDto basketDto) {
        List<BasketProduct>  basketProductList = basket.getBasketProductList();
        BasketProduct basketProduct = basketProductService.findByBasketIdAndProductId(basket.getId(),basketDto.getBasketProductDtoList().get(0).getProductId());
    }


    @Override
    public BasketDto get(String id) {
        return finBasketByUserId(id);
    }//basketin içindeki user ın id sine göre getirecek

    private BasketDto finBasketByUserId(String id) {
        return basketToDto(repository.findBasketByUser_Id(Long.parseLong(id)));
    }
    @Override
    public void delete(String id) {
        repository.deleteById(Long.parseLong(id));
    }

    @Override
    public BasketDto update(String id, BasketDto dto) {
        Basket existBasket = repository.findById(Long.parseLong(id))
                .orElseThrow(() -> new NoSuchElementException("No basket found with id: " + id));

        User user = userToEntity(dto.getUser());
        existBasket.setUser(user);

        return basketToDto(repository.save(existBasket)); // Güncellenmiş basket'i kaydedip, BasketDto olarak döndür
    }

    @Override
    public List<BasketDto> getAll() {
        List<Basket> baskets = repository.findAll();

        //her bir basket entity sini dto ya çevirir ve döndürür
        return baskets.stream()
                .map(this::basketToDto)
                .collect(Collectors.toList());

    }

    private Basket basketToEntity(BasketDto dto) {
        Basket basket= new Basket();

        basket.setId(dto.getId());
        if(dto.getUser() != null){
            basket.setUser(userToEntity(dto.getUser()));
        }

        return basket;
    }

    private User userToEntity(UserDto dto){
        User user =new User();

        user.setId(dto.getId());
        user.setUserName(dto.getUserName());
        user.setFullName(dto.getFullName());
        user.setBirthDate(dto.getBirthDate());
        user.setTelNo(dto.getTelNo());
        user.setPassword(dto.getPassword());
        user.setBirthPlace(dto.getBirthPlace());
        user.setTckn(dto.getTckn());

        return user;

    }

    private BasketDto basketToDto(Basket basket) {
        BasketDto dto =new BasketDto();

        dto.setId(basket.getId());
        if(basket.getUser() != null){
            dto.setUser(userToDto(basket.getUser()));
        }

        return dto;
    }
    private UserDto userToDto(User user){
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setFullName(user.getFullName());
        userDto.setBirthDate(user.getBirthDate());
        userDto.setTelNo(user.getTelNo());
        userDto.setPassword(user.getPassword());
        userDto.setBirthPlace(user.getBirthPlace());
        userDto.setTckn(user.getTckn());

        return userDto;
    }
}




