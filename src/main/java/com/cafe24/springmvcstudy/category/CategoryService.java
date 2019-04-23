package com.cafe24.springmvcstudy.category;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class CategoryService {

    @Cacheable("category")
    public List<Category> getCategory() {
        log.debug("getCategory() call");
        Category category = new Category();
        category.setName("고고");
        return Collections.singletonList(category);
    }

    @Cacheable(value = "category", key = "#categoryName")
    public List<Category> getCategory(String categoryName) {
        log.debug("getCategory(String categoryName) call");
        Category category = new Category();
        category.setName("고고");
        return Collections.singletonList(category);
    }

    @Cacheable(value = "category", key = "{#categoryName, #subCategoryName}")
    public List<Category> getCategory(String categoryName, String subCategoryName) {
        log.debug("getCategory(String categoryName, String subCategoryName) call");
        Category category = new Category();
        category.setName("고고");
        return Collections.singletonList(category);
    }

    @Cacheable(value = "category")
    public List<Category> getCategory(String categoryName, String subCategoryName, String depth) {
        log.debug("getCategory(String categoryName, String subCategoryName, String depth) call");
        Category category = new Category();
        category.setName("고고");
        return Collections.singletonList(category);
    }

    @CacheEvict("category")
    public void deleteCategoryCache() {
        log.debug("deleteCategoryCache() call");
    }
}
