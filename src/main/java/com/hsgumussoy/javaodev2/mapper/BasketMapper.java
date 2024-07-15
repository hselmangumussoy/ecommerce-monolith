package com.hsgumussoy.javaodev2.mapper;

import com.hsgumussoy.javaodev2.dto.BasketDto;
import com.hsgumussoy.javaodev2.entity.Basket;
import com.hsgumussoy.javaodev2.service.impl.UserServiceImpl;
import com.hsgumussoy.javaodev2.request.BasketRequest;
import com.hsgumussoy.javaodev2.response.BasketResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class BasketMapper {
    private UserServiceImpl userService;

    private BasketProductMapper basketProductMapper;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setBasketProductMapper(BasketProductMapper basketProductMapper) {
        this.basketProductMapper = basketProductMapper;
    }
    public BasketDto requestToDto(BasketRequest request) {
        return BasketDto.builder()
                .userId(request.getUserId())
                .count(request.getCount())
                .productId(request.getProductId())
                .build();
    }

    public BasketResponse dtoToResponse(BasketDto dto) {
        return BasketResponse.builder()
                .id(dto.getId())
                .status(dto.getStatus())
                .totalPrice(dto.getTotalPrice())
                .basketProductList(dto.getBasketProductList())
                .userId(dto.getUserId())
                .build();
    }

    public List<BasketResponse> mapDtosToResponses(List<BasketDto> basketDtoList) {
        return basketDtoList.stream()
                .map(this::dtoToResponse)
                .collect(Collectors.toList());
    }

    public BasketDto entityToDto(Basket basket) {
        BasketDto.BasketDtoBuilder builder  = BasketDto.builder()
                .id(basket.getId())
                .userId(basket.getUser().getId())
                .status(basket.getStatus())
                .totalPrice(basket.getTotalPrice());
        if(basket.getBasketProductList() != null){
            builder.basketProductList(basketProductMapper.mapEntitesToDtos(basket.getBasketProductList()));
        }else {
            builder.basketProductList(Collections.emptyList()); // veya isteğe bağlı bir değer atayabilirsiniz
        }
        return builder.build();
    }


    public Basket dtoToEntity(BasketDto dto) {
        return Basket.builder()
                .status(dto.getStatus())
                .totalPrice(dto.getTotalPrice())
                .user(userService.findUserById(dto.getUserId()))
                .basketProductList(dto.getBasketProductList() != null ?
                        basketProductMapper.mapDtosToEntities(dto.getBasketProductList()) : Collections.emptyList())
                .build();
    }


}
