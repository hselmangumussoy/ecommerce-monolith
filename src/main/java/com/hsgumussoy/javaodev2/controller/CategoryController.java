package com.hsgumussoy.javaodev2.controller;


import com.hsgumussoy.javaodev2.mapper.CategoryMapper;
import com.hsgumussoy.javaodev2.request.CategoryRequest;
import com.hsgumussoy.javaodev2.response.CategoryResponse;
import com.hsgumussoy.javaodev2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService service;
    @Autowired
    private CategoryMapper categoryMapper;

    @PostMapping
    public CategoryResponse save(@RequestBody CategoryRequest request) {
        return categoryMapper.dtoToResponse(service.save(categoryMapper.requestToDto(request)));
    }


    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Long id) {
        return categoryMapper.dtoToResponse(service.getById(id));
    }


    @GetMapping
    public List<CategoryResponse> getAll(){
        return categoryMapper.mapCategoryResponses(service.getAll());
    }

    @DeleteMapping("/{id}")
    public CategoryResponse delete(@PathVariable(name = "id") String id) {
        return categoryMapper.dtoToResponse(service.delete(id));
    }

    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable(name = "id") String id, @RequestBody CategoryRequest request) {
        return categoryMapper.dtoToResponse(service.update(id, categoryMapper.requestToDto(request)));
    }


}
