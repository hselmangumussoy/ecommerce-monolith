package com.hsgumussoy.javaodev2.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketProductDto {
    private Long id;
    private int count;
    private double totalAmount;
    private Long productId;
    private Long basketId;

}
