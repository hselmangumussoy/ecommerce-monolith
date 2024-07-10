package com.hsgumussoy.javaodev2.impl;

import com.hsgumussoy.javaodev2.dto.ProductDto;
import com.hsgumussoy.javaodev2.entity.Product;
import com.hsgumussoy.javaodev2.exception.RecordNotFoundExceptions;
import com.hsgumussoy.javaodev2.mapper.ProductMapper;
import com.hsgumussoy.javaodev2.repository.ProductRepository;
import com.hsgumussoy.javaodev2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository repository;
    @Autowired
    private ProductMapper productMapper;

    public ProductDto save(ProductDto dto) {
        return productMapper.productToDto(repository.save(productMapper.dtoToEntity(dto)));
    }

    public ProductDto get(String id) {
        return productMapper.productToDto(repository.findById(Long.parseLong(id)).orElseThrow(() -> (new RecordNotFoundExceptions(5000, "Product not found"))));
    }

    public void delete(String id) {
        repository.deleteById(Long.parseLong(id));
    }

    public ProductDto update(String id, ProductDto dto) {
        // String id'yi Long id'ye çeviriyoruz ve kategori var mı diye kontrol ediyoruz
        Product existProduct = repository.findById(Long.parseLong(id))
                .orElseThrow(() -> new RecordNotFoundExceptions(4000, "Product not found with id" + id));

        // Mevcut product bilgilerini dto'dan gelen bilgilerle güncelliyoruz
        Product updateProduct = productMapper.dtoToEntity(dto);
        updateProduct.setId(existProduct.getId());// ID'nin korunması gerekebilir, çünkü yeni bir entity yaratıyoruz


        return productMapper.entityToDto(repository.save(updateProduct));
    }

    @Override
    public List<ProductDto> getAll() {
        List<ProductDto> productDtoList = new ArrayList<>();

        for (Product product : repository.findAll()) {
            productDtoList.add(productMapper.entityToDto(product));
        }
        return productDtoList;
    }


    public Product findProductById(Long productId) {
        if(productId == null){
            throw  new IllegalArgumentException("The given id must not be null");
        }
        return repository.findById(productId).orElseThrow();
    }
}


