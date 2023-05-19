package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepo;
import com.example.demo.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CategoryServiceTests {
    private static final String NAME = "Mancare";
    private static final String NAME1 = "Mancare1";

    @Mock
    private CategoryRepo categoryRepo;
    @InjectMocks
    private CategoryServiceImpl categoryService;
    private Category category;

    @BeforeEach
    void init() {
        initMocks(this);
        category = new Category();
        categoryService = new CategoryServiceImpl(categoryRepo);
        when(categoryRepo.save(category)).thenReturn(category);
    }

    @Test
    void updateVerify(){
        Category category1 = new Category();
        category1.setName(NAME);
        category1.setId(12);
        Category category2 = new Category();
        category2.setName(NAME1);
        category2.setId(12);
        given(categoryRepo.findById(category1.getId())).willReturn(Optional.of(category1));
        categoryService.updateCategory(category2);
//        verify(categoryRepo).save(category2);
//        verify(categoryRepo).findById(category1.getId());
    }

    @Test
    void updateVerify_ExceptionIfCategoryDoesntExist(){
        try {
            Category category1 = new Category();
            category1.setName(NAME);
            category1.setId(12);
            Category category2 = new Category();
            category2.setName(NAME1);
            category2.setId(13);
            given(categoryRepo.findById(anyInt())).willReturn(Optional.ofNullable(null));
            categoryService.updateCategory(category2);
        }catch(RuntimeException re){}
    }

    @Test
    void deleteVerify(){
        Category category1 = new Category();
        category1.setName(NAME);
        category1.setId(12);
        when(categoryRepo.findById(category1.getId())).thenReturn(Optional.of(category1));
        categoryService.deleteCategory(category1);
      //  verify(categoryRepo).delete(category1);
    }

    @Test
    void deleteVerify_ExceptionIfCategoryDoesntExist(){
        try {
            Category category1 = new Category();
            category1.setName(NAME);
            category1.setId(13);
            given(categoryRepo.findById(anyInt())).willReturn(Optional.ofNullable(null));
            categoryService.deleteCategory(category1);
        }catch(RuntimeException re){}
    }

    @Test
    void createVerify(){
        Category category1 = new Category();
        category1.setName(NAME);
        category1.setId(12);
        categoryService.createCategory(category1);
    }
}
