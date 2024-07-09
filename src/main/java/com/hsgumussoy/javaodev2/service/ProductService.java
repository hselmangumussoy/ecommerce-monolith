package com.hsgumussoy.javaodev2.service;

import com.hsgumussoy.javaodev2.dto.ProductDto;

import java.util.List;

public interface ProductService {
    public ProductDto save(ProductDto dto);
    public ProductDto get(String id);
    public void delete(String id);
    ProductDto update(String id, ProductDto dto);
    List<ProductDto> getAll();

    //public List<Product>getAll();
}
