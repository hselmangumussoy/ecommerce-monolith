package com.hsgumussoy.javaodev2.mapper;

import com.hsgumussoy.javaodev2.dto.ProductDto;
import com.hsgumussoy.javaodev2.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public Product dtoToEntity(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.getCategory().setId(dto.getCategoryId());
        return product;
    }
    public List<Product> mapDtosToEntity(List<ProductDto> productDtos){
        return productDtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}
