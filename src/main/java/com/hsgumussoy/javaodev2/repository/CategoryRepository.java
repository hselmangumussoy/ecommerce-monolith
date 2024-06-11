package com.hsgumussoy.javaodev2.repository;

import com.hsgumussoy.javaodev2.entity.Category;
import com.hsgumussoy.javaodev2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findCategoryByCategoryId(int id);

    Category deleteCategoryById(int id);

}
