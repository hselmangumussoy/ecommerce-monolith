package com.hsgumussoy.javaodev2.mapper;

import com.hsgumussoy.javaodev2.dto.BasketProductDto;
import com.hsgumussoy.javaodev2.entity.BasketProduct;
import com.hsgumussoy.javaodev2.impl.BasketServiceImpl;
import com.hsgumussoy.javaodev2.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class BasketProductMapper {
    @Autowired
    private BasketServiceImpl basketService;
    @Autowired
    private ProductServiceImpl productService;

    public List<BasketProduct> mapDtosToEntites(List<BasketProductDto> basketProductDtoList) {
        return basketProductDtoList.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }

    public BasketProduct dtoToEntity(BasketProductDto basketProductDto) {
        return BasketProduct.builder()
                .count(basketProductDto.getCount())
                .id(basketProductDto.getId())
                .totalAmount(basketProductDto.getTotalAmount())
                .product(productService.findProductById(basketProductDto.getProductId()))
                .basket(basketService.findBasketById(basketProductDto.getBasketId()))
                .build();
    }
    public List<BasketProductDto> mapEntitesToDtos(List<BasketProduct> basketProductList){
        return basketProductList.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
    public BasketProductDto entityToDto(BasketProduct basketProduct){
        return BasketProductDto.builder()
                .basketId(basketProduct.getBasket().getId())
                .productId(basketProduct.getProduct().getId())
                .count(basketProduct.getCount())
                .id(basketProduct.getId())
                .build();
    }
}
