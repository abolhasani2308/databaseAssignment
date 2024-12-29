package com.databaseassignment.services;

import com.databaseassignment.entities.Category;
import com.databaseassignment.repositories.CategoryRepository;

import java.util.List;

public class CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryService() {
        this.categoryRepository = new CategoryRepository();
    }

    public void createCategory(String name) {
        Category category = new Category();
        category.setName(name);
        categoryRepository.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll("from Category", Category.class);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(Category.class, id);
    }

    public void updateCategory(Long id, String name) {
        Category category = categoryRepository.findById(Category.class, id);
        if (category != null) {
            category.setName(name);
            categoryRepository.update(category);
        } else {
            System.out.println("Category not found!");
        }
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(Category.class, id);
        if (category != null) {
            categoryRepository.delete(category);
        } else {
            System.out.println("Category not found!");
        }
    }

    public void close() {
        categoryRepository.close();
    }
}
