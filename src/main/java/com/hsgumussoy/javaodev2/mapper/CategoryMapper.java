package com.hsgumussoy.javaodev2.mapper;

import com.hsgumussoy.javaodev2.dto.CategoryDto;
import com.hsgumussoy.javaodev2.entity.Category;
import com.hsgumussoy.javaodev2.request.CategoryRequest;
import com.hsgumussoy.javaodev2.response.CategoryResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component

public class CategoryMapper {

    private final ProductMapper productMapper;

    public CategoryMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public CategoryResponse dtoToResponse(CategoryDto dto) {
        return CategoryResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .descrpition(dto.getDescription())
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
                .name(dto.getName())
                .description(dto.getDescription())
                .build();

    }

    public CategoryDto entityToDto(Category category) {
        CategoryDto.CategoryDtoBuilder builder = CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription());

        if (category.getProductList() != null) {
            builder.productList(productMapper.mapEntitiesToDtos(category.getProductList()));
        } else {
            builder.productList(null); // veya isteğe bağlı bir değer atayabilirsiniz
        }

        return builder.build();
    }

}
