package com.hsgumussoy.javaodev2.mapper;

import com.hsgumussoy.javaodev2.dto.ProductDto;
import com.hsgumussoy.javaodev2.entity.Product;
import com.hsgumussoy.javaodev2.request.ProductRequest;
import com.hsgumussoy.javaodev2.response.ProductResponse;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
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
                .categoryId(request.getCategoryId())
                .build();

    }
    public Product dtoToEntity(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.getCategory().setId(dto.getCategoryId());
        return product;
    }
    public List<Product> mapDtosToEntity(List<ProductDto> productDtos){
        return productDtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }

    public ProductDto productToDto(Product product) {
        return ProductDto.builder()
                .name(product.getName())
                .price(product.getPrice())
                .id(product.getId())
                .categoryId(product.getCategory().getId())
                .description(product.getDescription())
                .build();
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
}
