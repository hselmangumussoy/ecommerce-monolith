package com.hsgumussoy.javaodev2.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketProductResponse {
    private Long id;
    private int count;
    private double totalAmount;
    private Long basketId;
    private Long productId;
}