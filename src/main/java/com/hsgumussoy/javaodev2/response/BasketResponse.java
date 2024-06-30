package com.hsgumussoy.javaodev2.response;

import com.hsgumussoy.javaodev2.dto.BasketProductDto;
import com.hsgumussoy.javaodev2.entity.BasketProduct;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketResponse  {
    private Long id;
    private  int status;
    private double totalPrice;
    private Long userId;
    private List<BasketProductDto>basketProductDtoList;

}
