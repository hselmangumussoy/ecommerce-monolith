package com.hsgumussoy.javaodev2.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddProductToBasketRequest {
    private Long userId;
    private  Long productId;
    private int count;

}
