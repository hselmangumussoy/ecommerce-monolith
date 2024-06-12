package com.hsgumussoy.javaodev2.controller;

import com.hsgumussoy.javaodev2.dto.ProductDto;
import com.hsgumussoy.javaodev2.request.ProductRequest;
import com.hsgumussoy.javaodev2.response.ProductResponse;
import com.hsgumussoy.javaodev2.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductServiceImpl service;

    @PostMapping
    public ProductResponse save(@RequestBody ProductRequest request) {
        return toResponse(service.save(toDto(request)));
    }


    @GetMapping("/{id}")
    public ProductResponse get(@PathVariable(name= "id") String id){
        return toResponse(service.get(id));
    }

    /*
    @GetMapping
    public List<CategoryResponse> getAll(){
        return toResponse(service.getAll());
    }*/

    @DeleteMapping("/{id}")
    public ProductResponse delete(@PathVariable(name= "id") String id){
        return toResponse( service.delete(id));
    }
    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable(name= "id") String id, @RequestBody ProductRequest request){
        return toResponse(service.update(id, toDto(request)));
    }



    private ProductResponse toResponse(ProductDto dto) {

        return ProductResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .categoryId(dto.getCategoryId())
                .build();
    }

    private ProductDto toDto(ProductRequest request) {

        return ProductDto.builder()
                .name(request.getName())
                .categoryId(request.getCategoryId())
                .build();

    }
}
