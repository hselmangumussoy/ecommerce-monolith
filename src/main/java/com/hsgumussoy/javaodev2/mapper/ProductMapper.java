package com.hsgumussoy.javaodev2.mapper;

import com.hsgumussoy.javaodev2.dto.ProductDto;
import com.hsgumussoy.javaodev2.entity.Product;
import com.hsgumussoy.javaodev2.impl.CategoryServiceImpl;
import com.hsgumussoy.javaodev2.request.ProductRequest;
import com.hsgumussoy.javaodev2.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    @Autowired
    @Lazy
    private CategoryServiceImpl categoryService;
    public ProductResponse dtoToResponse(ProductDto dto) {
        return ProductResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .categoryId(dto.getCategoryId())
                .build();
    }

    public List<ProductResponse> dtosToResponses(List<ProductDto> dtos) {
        return dtos.stream()
                .map(this::dtoToResponse)
                .collect(Collectors.toList());
    }
    public ProductDto requestToDto(ProductRequest request) {
        return ProductDto.builder()
                .name(request.getName())
                .price(request.getPrice())
                .name(request.getName())
                .description(request.getDescription())
                .categoryId(request.getCategoryId())
                .build();

    }
    public Product dtoToEntity(ProductDto dto) {
        return Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .category(categoryService.findCategoryById(dto.getCategoryId()))
                .description(dto.getDescription())
                .build();
    }
    public List<Product> mapDtosToEntity(List<ProductDto> productDtos){
        return productDtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }


    public ProductDto entityToDto(Product product) {
        return ProductDto.builder()
                .description(product.getDescription())
                .id(product.getId())
                .price(product.getPrice())
                .name(product.getName())
                .categoryId(product.getCategory().getId())
                .build();
    }

    public List<ProductDto> mapEntitiesToDtos(List<Product> products) {
        return products.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
}
