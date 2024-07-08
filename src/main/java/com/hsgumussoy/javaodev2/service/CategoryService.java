package com.hsgumussoy.javaodev2.service;


import com.hsgumussoy.javaodev2.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    public CategoryDto save(CategoryDto dto);
    public void delete(String id);
    public CategoryDto update(String id, CategoryDto dto);
    List<CategoryDto> getAll();
    public CategoryDto get(String id);

    //public List<Category>getAll();
}
