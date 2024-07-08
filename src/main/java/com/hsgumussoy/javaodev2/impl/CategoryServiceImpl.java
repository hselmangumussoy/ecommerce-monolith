package com.hsgumussoy.javaodev2.impl;

import com.hsgumussoy.javaodev2.dto.CategoryDto;
import com.hsgumussoy.javaodev2.entity.Category;
import com.hsgumussoy.javaodev2.exception.RecordNotFoundExceptions;
import com.hsgumussoy.javaodev2.mapper.CategoryMapper;
import com.hsgumussoy.javaodev2.repository.CategoryRepository;
import com.hsgumussoy.javaodev2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository repository;
    @Autowired
    CategoryMapper categoryMapper;


    public CategoryDto save(CategoryDto dto) {
        return  categoryMapper.entityToDto(repository.save(categoryMapper.dtoToEntity(dto)));
    }
    @Override
    public CategoryDto get(String id) {
        return categoryMapper.entityToDto(repository.findById(Long.parseLong(id))
                .orElseThrow(()-> new RecordNotFoundExceptions(4000,"Category not found with id"+id)));
    }

    public void delete(String id) {
        repository.findById(Long.parseLong(id));
    }

    public CategoryDto update(String id, CategoryDto dto) {
        Category existCategory = repository.findById(Long.parseLong(id))
                .orElseThrow(()-> new RecordNotFoundExceptions(4000, "Category not found with id"+id));

        return categoryMapper.entityToDto(existCategory);
    }

    @Override
    public List<CategoryDto> getAll() {
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        for(Category category: repository.findAll()){
            categoryDtoList.add(categoryMapper.entityToDto(category));
        }
        return categoryDtoList;
    }




    /* public List<UserDto> getAll() {
         return repository.findAll();
     }*/
    public Category findById(Long id){
        return repository.findById(id).orElseThrow(() ->(new RecordNotFoundExceptions(4000,"Category not found")));
    }





}
