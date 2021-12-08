package com.gameforum.service.category;

import com.gameforum.dto.publication.CategoryDto;
import com.gameforum.model.category.Category;
import com.gameforum.repository.publication.CategoryRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepos categoryRepos;

    @Autowired
    public CategoryService(CategoryRepos categoryRepos) {
        this.categoryRepos = categoryRepos;
    }

    public void addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryName(categoryDto.getCategoryName());
        categoryRepos.save(category);
    }

    public void updateCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryName(categoryDto.getCategoryName());
        categoryRepos.save(category);
    }

    public void deleteCategoryById(Long categoryId) {
        categoryRepos.deleteById(categoryId);
    }

    public void deleteCategoryByName(String categoryName) {
        categoryRepos.deleteCategoryByCategoryName(categoryName);
    }
}
