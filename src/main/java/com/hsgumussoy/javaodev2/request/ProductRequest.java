package com.hsgumussoy.javaodev2.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private Long categoryId;
}
