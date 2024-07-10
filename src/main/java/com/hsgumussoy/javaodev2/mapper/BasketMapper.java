package com.hsgumussoy.javaodev2.mapper;

import com.hsgumussoy.javaodev2.dto.BasketDto;
import com.hsgumussoy.javaodev2.dto.BasketProductDto;
import com.hsgumussoy.javaodev2.entity.Basket;
import com.hsgumussoy.javaodev2.entity.BasketProduct;
import com.hsgumussoy.javaodev2.impl.BasketServiceImpl;
import com.hsgumussoy.javaodev2.impl.ProductServiceImpl;
import com.hsgumussoy.javaodev2.impl.UserServiceImpl;
import com.hsgumussoy.javaodev2.request.BasketRequest;
import com.hsgumussoy.javaodev2.response.BasketResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BasketMapper {
    @Autowired
    @Lazy
    private UserServiceImpl userService;
    @Autowired
    @Lazy
    private BasketProductMapper basketProductMapper;

    public BasketDto requestToDto(BasketRequest request) {
        return BasketDto.builder()
                .status(request.getCount())
                .totalPrice(request.getTotalPrice())
                .userId(request.getUserId())
                .build();
    }

    public BasketResponse dtoToResponse(BasketDto dto) {
        return BasketResponse.builder()
                .id(dto.getId())
                .status(dto.getStatus())
                .totalPrice(dto.getTotalPrice())
                .basketProductDtoList(dto.getBasketProductDtoList())
                .userId(dto.getUserId())
                .build();
    }

    public List<BasketResponse> mapDtosToResponses(List<BasketDto> basketDtoList) {
        return basketDtoList.stream()
                .map(this::dtoToResponse)
                .collect(Collectors.toList());
    }

    public BasketDto entityToDto(Basket basket) {
        return BasketDto.builder()
                .id(basket.getId())
                .userId(basket.getUser().getId())
                .status(basket.getStatus())
                .totalPrice(basket.getTotalPrice())
                .basketProductDtoList(basketProductMapper.mapEntitesToDtos(basket.getBasketProductList()))
                .build();
    }


    public Basket dtoToEntity(BasketDto dto) {
        return Basket.builder()
                .status(dto.getStatus())
                .totalPrice(dto.getTotalPrice())
                .user(userService.findUserById(dto.getUserId()))
                .basketProductList(basketProductMapper.mapDtosToEntites(dto.getBasketProductDtoList()))
                .build();
    }


}
