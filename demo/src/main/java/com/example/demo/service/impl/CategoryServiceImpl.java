package com.example.demo.service.impl;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;
    private CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepo categoryRepo){
        this.categoryRepo = categoryRepo;
        categoryMapper = new CategoryMapper();
    }

    @Override
    public Category updateCategory(Category category) {
        Category updateCategory = categoryRepo.findById(category.getId()).get();
        updateCategory.setName(category.getName());
        updateCategory.setProducts(category.getProducts());
        updateCategory.setId(category.getId());
        categoryRepo.save(updateCategory);
        return category;
    }

    @Override
    public Category deleteCategory(Category category) {
        Category deleteCategory = categoryRepo.findById(category.getId()).get();
        categoryRepo.delete(deleteCategory);
        return category;
    }

    @Override
    public Category createCategory(Category category) {
        categoryRepo.save(category);
        return category;
    }

    @Override
    public CategoryDTO findByIdDTO(int id) {
        final Category category = categoryRepo.findById(id).orElseThrow(()
                ->
        {
            throw new EntityNotFoundException("Nu se poate gasi categoria cu Id-ul:" + id);
        });
        return categoryMapper.mapModelToDto(category);
    }

    @Override
    public Category findById(int id) {
        return categoryRepo.findById(id).get();
    }

    @Override
    public List<CategoryDTO> findAll() {
        return categoryRepo.findAll()
                .stream().map(
                        CategoryMapper::mapModelToDto
                ).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findByName(String name) {
        final Category category = categoryRepo.findAllByName(name).orElseThrow(()
                -> {
            throw new EntityNotFoundException("Can not find category ");
        });
        return CategoryMapper.mapModelToDto(category);
    }

    @Override
    public Optional<Category> findAllByName(String name) {
        return categoryRepo.findAllByName(name);
    }
}
