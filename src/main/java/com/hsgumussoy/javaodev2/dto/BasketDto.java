package com.hsgumussoy.javaodev2.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketDto {
    private Long id;
    private int status;
    private double totalPrice;
    private Long userId;
    private List<BasketProductDto> basketProductList;



}
