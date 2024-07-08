package com.hsgumussoy.javaodev2.impl;

import com.hsgumussoy.javaodev2.dto.CategoryDto;
import com.hsgumussoy.javaodev2.entity.Category;
import com.hsgumussoy.javaodev2.exception.RecordNotFoundExceptions;
import com.hsgumussoy.javaodev2.mapper.CategoryMapper;
import com.hsgumussoy.javaodev2.repository.CategoryRepository;
import com.hsgumussoy.javaodev2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository repository;
    @Autowired
    CategoryMapper categoryMapper;


    public CategoryDto save(CategoryDto dto) {
        return  categoryMapper.requestToDto(repository.save(categoryMapper.(dto)));
    }


    @Override
    public CategoryDto getById(Long id) {
        return toDto(repository.findById(id).orElseThrow(RuntimeException::new));
    }

    public void delete(String id) {
        Category category = repository.deleteCategoryById(Long.parseLong(id));
        return toDto(category);
    }

    public CategoryDto update(String id, CategoryDto dto) {
        Category existCategory =repository.findCategoryById(Long.parseLong(id));
        if(existCategory != null){

        }
        else {
            return  null;
        }
        return toDto(repository.save(existCategory));
    }

    @Override
    public List<CategoryDto> getAll() {
        return null;
    }


    /* public List<UserDto> getAll() {
         return repository.findAll();
     }*/
    public Category findById(Long id){
        return repository.findById(id).orElseThrow(() ->(new RecordNotFoundExceptions(4000,"Category not found")));
    }





}
