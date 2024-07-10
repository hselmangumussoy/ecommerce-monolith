package com.hsgumussoy.javaodev2.mapper;

import com.hsgumussoy.javaodev2.dto.CategoryDto;
import com.hsgumussoy.javaodev2.dto.ProductDto;
import com.hsgumussoy.javaodev2.entity.Category;
import com.hsgumussoy.javaodev2.entity.Product;
import com.hsgumussoy.javaodev2.request.CategoryRequest;
import com.hsgumussoy.javaodev2.response.CategoryResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Component
public class CategoryMapper {
    @Autowired
    private ProductMapper productMapper;

    public CategoryResponse dtoToResponse(CategoryDto dto) {
        return CategoryResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .descirption(dto.getDescription())
                .productList(dto.getProductList())
                .build();
    }
    public List<CategoryResponse> mapDtosToResponses(List<CategoryDto> categoryDtoList){
        return categoryDtoList.stream()
                .map(this::dtoToResponse)
                .collect(Collectors.toList());
    }

    public CategoryDto requestToDto(CategoryRequest request) {
        return CategoryDto.builder()
                .name(request.getName())
                .description(request.getDescrpition())
                .build();

    }

    public Category dtoToEntity(CategoryDto dto) {
        return Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .productList(productMapper.mapDtosToEntity(dto.getProductList()))
                .build();

    }

    public CategoryDto entityToDto(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .productList(productMapper.mapEntitiesToDtos(category.getProductList()))
                .build();
    }
}
