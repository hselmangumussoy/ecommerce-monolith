package com.hsgumussoy.javaodev2.service;

import com.hsgumussoy.javaodev2.dto.CategoryDto;
import com.hsgumussoy.javaodev2.dto.ProductDto;

public interface ProductService {
    public ProductDto save(ProductDto dto);
    public ProductDto get(String id);
    public ProductDto delete(String id);
    public ProductDto update(String id, ProductDto dto);
    //public List<Product>getAll();
}
