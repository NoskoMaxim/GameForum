package com.gameforum.service.category;

import com.gameforum.config.gameforum.GameForumException;
import com.gameforum.dto.publication.CategoryDto;
import com.gameforum.model.category.Category;
import com.gameforum.repository.publication.CategoryRepos;
import org.hibernate.exception.ConstraintViolationException;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class CategoryService {

    private final CategoryRepos categoryRepos;

    @Autowired
    public CategoryService(CategoryRepos categoryRepos) {
        this.categoryRepos = categoryRepos;
    }

    @Transactional
    public void addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        try {
            categoryRepos.save(category);
        } catch (DataIntegrityViolationException exception) {
            Map<String, String> failures = new HashMap<>();
            failures.put("categoryName", "Category already exists");
            throw new GameForumException(failures);
        }
    }

    @Transactional
    public void updateCategory(CategoryDto categoryDto) {
        //Category category = new Category();
        categoryRepos.updateCategoryById(categoryDto.getCategoryName(),categoryDto.getCategoryId());
//        category.setCategoryId(categoryDto.getCategoryId());
//        category.setCategoryName(categoryDto.getCategoryName());
//        categoryRepos.save(category);
    }

    @Transactional
    public void deleteCategoryById(Long categoryId) {
        categoryRepos.deleteById(categoryId);
    }

    @Transactional
    public void deleteCategoryByName(String categoryName) {
        categoryRepos.deleteCategoryByCategoryName(categoryName);
    }
}
