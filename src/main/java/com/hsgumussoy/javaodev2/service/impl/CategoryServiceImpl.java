package com.hsgumussoy.javaodev2.service.impl;

import com.hsgumussoy.javaodev2.dto.CategoryDto;
import com.hsgumussoy.javaodev2.dto.ProductDto;
import com.hsgumussoy.javaodev2.entity.Category;
import com.hsgumussoy.javaodev2.entity.Product;
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



    public CategoryDto get(String id) {
        Category category = repository.findCategoryByCategoryId(Integer.parseInt(id));
        return toDto(category);
    }

    public CategoryDto delete(String id) {
        Category category = repository.deleteCategoryById(Integer.parseInt(id));
        return toDto(category);
    }

    public CategoryDto update(String id, CategoryDto dto) {
        Category existCategory =repository.findCategoryByCategoryId(Integer.parseInt(id));
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

        category.setProducts(products);

        return category;

    }
    private CategoryDto toDto(Category category) {
        CategoryDto dto = new CategoryDto();
        dto.setId(category.getId());
        dto.setName(category.getName());


        List<ProductDto> productDtos = category.getProducts().stream()
                .map(product -> {
                    ProductDto productDto = new ProductDto();
                    productDto.setId(product.getId());
                    productDto.setName(product.getName());
                    productDto.setCategoryId(product.getCategory().getId().intValue());
                    return productDto;
                })
                .collect(Collectors.toList());

        dto.setProductsList(productDtos);

        return dto;
    }

}
