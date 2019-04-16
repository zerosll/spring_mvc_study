package com.cafe24.springmvcstudy.category;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class CategoryService {

    @Cacheable("category")
    public List<Category> getCategory() {
        Category category = new Category();
        category.setName("고고");
        return Collections.singletonList(category);
    }

    @Cacheable(value = "category", key = "#categoryName")
    public List<Category> getCategory(String categoryName) {
        Category category = new Category();
        category.setName("고고");
        return Collections.singletonList(category);
    }

    @Cacheable(value = "category", key = "{#categoryName, #subCategoryName}")
    public List<Category> getCategory(String categoryName, String subCategoryName) {
        Category category = new Category();
        category.setName("고고");
        return Collections.singletonList(category);
    }

    @Cacheable(value = "category")
    public List<Category> getCategory(String categoryName, String subCategoryName, String depth) {
        Category category = new Category();
        category.setName("고고");
        return Collections.singletonList(category);
    }

    @CacheEvict("category")
    public void createCategory() {

    }
}
