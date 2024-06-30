package com.hsgumussoy.javaodev2.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private List<ProductDto> productsList;


}
