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
        repository.deleteById(Long.parseLong(id));
    }

    public CategoryDto update(String id, CategoryDto dto) {
        // String id'yi Long id'ye çeviriyoruz ve kategori var mı diye kontrol ediyoruz
        Category existCategory = repository.findById(Long.parseLong(id))
                .orElseThrow(()-> new RecordNotFoundExceptions(4000, "Category not found with id"+id));

        // Mevcut kategori bilgilerini dto'dan gelen bilgilerle güncelliyoruz
        Category updateCategory = categoryMapper.dtoToEntity(dto);
        updateCategory.setId(existCategory.getId());// ID'nin korunması gerekebilir, çünkü yeni bir entity yaratıyoruz


        return categoryMapper.entityToDto(repository.save(updateCategory));
    }

    @Override
    public List<CategoryDto> getAll() {
        List<CategoryDto> categoryDtoList = new ArrayList<>();

        for(Category category: repository.findAll()){
            categoryDtoList.add(categoryMapper.entityToDto(category));
        }
        return categoryDtoList;
    }

    public Category findCategoryById(Long categoryId){
        if(categoryId == null){
            throw  new IllegalArgumentException("The given id must not be null");
        }
        return repository.findById(categoryId).orElseThrow();    }





}
