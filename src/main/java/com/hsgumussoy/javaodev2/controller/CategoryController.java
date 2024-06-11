package com.hsgumussoy.javaodev2.controller;

import com.hsgumussoy.javaodev2.dto.CategoryDto;
import com.hsgumussoy.javaodev2.dto.ProductDto;
import com.hsgumussoy.javaodev2.request.CategoryRequest;
import com.hsgumussoy.javaodev2.response.CategoryResponse;
import com.hsgumussoy.javaodev2.response.ProductResponse;
import com.hsgumussoy.javaodev2.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryServiceImpl service;

    @PostMapping
    public CategoryResponse save(@RequestBody CategoryRequest request) {
        return toResponse(service.save(toDto(request)));
    }


    @GetMapping("/{id}")
    public CategoryResponse get(@PathVariable(name= "id") String id){
        return toResponse(service.get(id));
    }

    /*
    @GetMapping
    public List<CategoryResponse> getAll(){
        return toResponse(service.getAll());
    }*/

    @DeleteMapping("/{id}")
    public CategoryResponse delete(@PathVariable(name= "id") String id){
        return toResponse( service.delete(id));
    }
    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable(name= "id") String id, @RequestBody CategoryRequest request){
        return toResponse(service.update(id, toDto(request)));
    }



    private CategoryResponse toResponse(CategoryDto dto) {
        CategoryResponse response = new CategoryResponse();
        response.setId(dto.getId());
        response.setName(dto.getName());

        List<ProductResponse> productResponses = dto.getProductsList().stream()
                .map(productDto -> {
                    ProductResponse productResponse = new ProductResponse();
                    productResponse.setId(productDto.getId());
                    productResponse.setName(productDto.getName());
                    productResponse.setCategoryId(productDto.getCategoryId());
                    return productResponse;
                })
                .collect(Collectors.toList());

        response.setProductList(productResponses);

        return response;
    }

    private CategoryDto toDto(CategoryRequest request) {
        CategoryDto dto = new CategoryDto();
        dto.setId(request.getId());
        dto.setName(request.getName());

        List<ProductDto> productDtos = request.getProductList().stream()
                .map(productRequest -> {
                    ProductDto productDto = new ProductDto();
                    productDto.setId(productRequest.getId());
                    productDto.setName(productRequest.getName());
                    productDto.setCategoryId(productDto.getCategoryId());
                    return productDto;
                })
                .collect(Collectors.toList());
        dto.setProductsList(productDtos);

        return dto;
    }
}
