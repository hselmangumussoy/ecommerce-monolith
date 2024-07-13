package com.hsgumussoy.javaodev2.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketProductRequest {
    private Long productId;
    private int count;
    private double totalAmount;
}
