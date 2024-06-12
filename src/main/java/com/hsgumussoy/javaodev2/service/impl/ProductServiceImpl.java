package com.hsgumussoy.javaodev2.service.impl;

import com.hsgumussoy.javaodev2.dto.ProductDto;
import com.hsgumussoy.javaodev2.entity.Product;
import com.hsgumussoy.javaodev2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl {
    @Autowired
    private ProductRepository repository;

    public ProductDto save(ProductDto dto) {
        return  toDto(repository.save(toEntity(dto)));
    }



    public ProductDto get(String id) {
        Product product = repository.findProductByProductId(Long.parseLong(id));
        return toDto(product);
    }

    public ProductDto delete(String id) {
        Product product = repository.deleteProductById(Long.parseLong(id));
        return toDto(product);
    }

    public ProductDto update(String id, ProductDto dto) {
        Product existProduct =repository.findProductByProductId(Long.parseLong(id));
        if(existProduct != null) {
            existProduct.setId(dto.getId());
            existProduct.setName(dto.getName());
            //existProduct.setCategory(dto.getCategoryId());
        }
        else{
            return null;
        }
        return toDto(repository.save(existProduct));
    }

    /* public List<UserDto> getAll() {
         return repository.findAll();
     }*/
    private Product toEntity(ProductDto dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        //product.setCategory(dto.getCategoryId());
        return product;

    }
    private ProductDto toDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        //dto.setCategoryId((product.getCategory().getId());
        return dto;
    }
}


