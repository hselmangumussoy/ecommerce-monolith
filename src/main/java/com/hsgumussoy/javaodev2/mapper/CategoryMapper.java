package com.hsgumussoy.javaodev2.mapper;

import com.hsgumussoy.javaodev2.dto.CategoryDto;
import com.hsgumussoy.javaodev2.entity.Category;
import com.hsgumussoy.javaodev2.entity.Product;
import com.hsgumussoy.javaodev2.repository.ProductRepository;
import com.hsgumussoy.javaodev2.request.CategoryRequest;
import com.hsgumussoy.javaodev2.response.CategoryResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Component
public class CategoryMapper {
    public CategoryResponse dtoToResponse(CategoryDto dto) {

        return CategoryResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .descirption(dto.getDescription())
                .productList(dto.getProductsList())
                .build();
    }
    public List<CategoryResponse> mapCategoryResponses(List<CategoryDto> categoryDtoList){
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

    private Category dtoToEntity(CategoryDto dto) {
        Category category = new Category();
        category.setId(dto.getId());
        category.setName(dto.getName());

        List<Product> products = dto.getProductsList().stream()
                .map(productDto ->{
                    ProductRepository productRepository = null;//repository i parametre olarak almamak için yerel olarak oluşturdum
                    Product product = productRepository.findById(productDto.getId()).orElse(new Product());
                    product.setId(product.getId());
                    product.setName(product.getName());
                    product.setCategory(category);
                    return product;
                })
                .collect(Collectors.toList());

        category.setProductList(products);

        return category;

    }
    private CategoryDto entityToDto(Category category) {
        category.setProductList(category.getProductList());
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
}
