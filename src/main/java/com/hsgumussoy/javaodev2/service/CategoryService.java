package com.hsgumussoy.javaodev2.service;


import com.hsgumussoy.javaodev2.dto.CategoryDto;

public interface CategoryService {
    public CategoryDto save(CategoryDto dto);
    public CategoryDto delete(String id);
    public CategoryDto update(String id, CategoryDto dto);
    public CategoryDto getById(Long id);
    //public List<Category>getAll();
}
