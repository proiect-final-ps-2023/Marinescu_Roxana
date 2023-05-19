package com.example.demo.service;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface CategoryService {
    public Category updateCategory(Category category);
    public Category deleteCategory(Category category);
    public Category createCategory(Category category);

    CategoryDTO findByIdDTO(int id);
    Category findById(int id);
    List<CategoryDTO> findAll();
    CategoryDTO findByName(String name);
    Optional<Category> findAllByName(String name);
}
