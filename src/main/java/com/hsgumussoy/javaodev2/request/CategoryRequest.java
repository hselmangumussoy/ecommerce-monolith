package com.hsgumussoy.javaodev2.request;

import com.hsgumussoy.javaodev2.entity.Product;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryRequest {
    private String name;
    private String descrpition;
}
