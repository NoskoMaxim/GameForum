package com.gameforum.repository.publication;

import com.gameforum.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepos extends JpaRepository<Category, Long> {
    void deleteCategoryByCategoryName(String categoryName);
}