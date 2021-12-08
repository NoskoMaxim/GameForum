package com.gameforum.repository.publication;

import com.gameforum.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepos extends JpaRepository<Category, Long> {
    void deleteCategoryByCategoryName(String categoryName);

    @Modifying
    @Query(value = "update Category category set category.categoryName = :categoryName where category.categoryId = :categoryId")
    void updateCategoryById(@Param("categoryName") String categoryName , @Param("categoryId") Long categoryId);
}