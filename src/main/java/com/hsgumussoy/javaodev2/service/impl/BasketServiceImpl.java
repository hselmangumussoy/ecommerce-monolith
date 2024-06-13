package com.hsgumussoy.javaodev2.service.impl;

import com.hsgumussoy.javaodev2.dto.BasketDto;
import com.hsgumussoy.javaodev2.dto.UserDto;
import com.hsgumussoy.javaodev2.entity.Basket;
import com.hsgumussoy.javaodev2.entity.User;
import com.hsgumussoy.javaodev2.repository.BasketRepository;
import com.hsgumussoy.javaodev2.service.BasketService;
import com.hsgumussoy.javaodev2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class BasketServiceImpl implements BasketService {

    @Autowired
    private BasketRepository repository;

    @Autowired
    UserService userService;

    public BasketDto save(BasketDto dto) {
        return toDto(repository.save(toEntity(dto)));
    }

    public BasketDto get(String id) {
        return toDto(finBasketByUserId(id));
    }
    private Basket finBasketByUserId(String id) {
        return repository.findBasketByUser_Id(Long.parseLong(id));
    }
    public void delete(String id) {
        repository.deleteById(Long.parseLong(id));
    }

    public BasketDto update(String id,BasketDto dto) {
        Basket existBasket = repository.findById(Long.parseLong(id))
                .orElseThrow(() -> new NoSuchElementException("No basket found with id: " + id));
        UserDto userDto  = userService.get(String.valueOf(dto.getUser().getId()));

        existBasket.setId(dto.getId());
        existBasket.setUser(UserMapper.toEntity(userDto)) ;//


        return toDto(existBasket);
    }
    /*public static class UserMapper {
        public static User toEntity(UserDto userDto) {
            if (userDto == null) {
                return null;
            }
            User user = new User();
            user.setId(userDto.getId());
            user.setUserName(userDto.getUserName());
            return user;
        }
    }*/
    private Basket toEntity(BasketDto dto) {
        Basket basket = new Basket();
        basket.setId(dto.getId());
        basket.setUser(UserMapper.toEntity(dto.getUser()));
        return basket;
    }

    private BasketDto toDto(Basket basket) {
        BasketDto dto = new BasketDto();
        dto.setId(basket.getId());
        dto.setUser(UserMapper.toDto(basket.getUser()));
        return dto;
    }

    public static class UserMapper {
        public static User toEntity(UserDto userDto) {
            if (userDto == null) {
                return null;
            }
            User user = new User();
            user.setId(userDto.getId());
            user.setUserName(userDto.getUserName());
            return user;
        }

        public static UserDto toDto(User user) {
            if (user == null) {
                return null;
            }
            UserDto dto = new UserDto();
            dto.setId(user.getId());
            dto.setUserName(user.getUserName());
            return dto;
        }
    }

}
