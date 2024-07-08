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
                .productList(dto.getProductsList())
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
        Category category = new Category();

        category.setName(dto.getName());
        category.setDescription(dto.getDescription());
        category.setProductList(productMapper.mapDtosToEntity(dto.getProductsList()));

        return category;

    }

    public CategoryDto entityToDto(Category category) {
        category.setProductList(category.getProductList());
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                //.productsList(category.getProductList())
                .build();
    }
}
