package com.hsgumussoy.javaodev2.response;

import com.hsgumussoy.javaodev2.dto.ProductDto;
import com.hsgumussoy.javaodev2.entity.Product;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
    private Long id;
    private String name;
    private String descrpition;
    private List<ProductDto> productList;
}
