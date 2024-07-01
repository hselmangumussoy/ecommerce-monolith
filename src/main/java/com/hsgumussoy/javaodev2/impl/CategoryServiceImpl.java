package com.hsgumussoy.javaodev2.impl;

import com.hsgumussoy.javaodev2.dto.CategoryDto;
import com.hsgumussoy.javaodev2.dto.ProductDto;
import com.hsgumussoy.javaodev2.entity.Category;
import com.hsgumussoy.javaodev2.entity.Product;
import com.hsgumussoy.javaodev2.exception.RecordNotFoundExceptions;
import com.hsgumussoy.javaodev2.repository.CategoryRepository;
import com.hsgumussoy.javaodev2.repository.ProductRepository;
import com.hsgumussoy.javaodev2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository repository;

    public CategoryDto save(CategoryDto dto) {
        return  toDto(repository.save(toEntity(dto)));
    }


    @Override
    public CategoryDto getById(Long id) {
        return toDto(repository.findById(id).orElseThrow(RuntimeException::new));
    }

    public CategoryDto delete(String id) {
        Category category = repository.deleteCategoryById(Long.parseLong(id));
        return toDto(category);
    }

    public CategoryDto update(String id, CategoryDto dto) {
        Category existCategory =repository.findCategoryById(Long.parseLong(id));
        if(existCategory != null){

        }
        else {
            return  null;
        }
        return toDto(repository.save(existCategory));
    }




    /* public List<UserDto> getAll() {
         return repository.findAll();
     }*/
    public Category findById(Long id){
        return repository.findById(id).orElseThrow(() ->(new RecordNotFoundExceptions(4000,"Category not found")));
    }


    private Category toEntity(CategoryDto dto) {
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
    private CategoryDto toDto(Category category) {
        category.setProductList(category.getProductList());
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }
    private Product toEntityForProduct(ProductDto dto){
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .build();
    }

}
