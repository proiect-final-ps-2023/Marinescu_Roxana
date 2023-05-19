package com.example.demo.mapper;
import com.example.demo.DTO.CategoryDTO;
import com.example.demo.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public static CategoryDTO mapModelToDto(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());

        return categoryDTO;
    }

    public static Category mapDtoToModel(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());

        return category;
    }
}
