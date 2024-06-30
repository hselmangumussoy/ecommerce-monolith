package com.hsgumussoy.javaodev2.controller;

import com.hsgumussoy.javaodev2.dto.CategoryDto;
import com.hsgumussoy.javaodev2.dto.ProductDto;
import com.hsgumussoy.javaodev2.request.CategoryRequest;
import com.hsgumussoy.javaodev2.response.CategoryResponse;
import com.hsgumussoy.javaodev2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService service;

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

        return CategoryResponse.builder()
                .id(dto.getId())
                .name(dto.getName())
                .descirption(dto.getDescription())
                .productList(dto.getProductsList())
                .build();
    }

    private CategoryDto toDto(CategoryRequest request) {

        return CategoryDto.builder()
                .name(request.getName())
                .description(request.getDescrpition())
                .build();

    }
}
