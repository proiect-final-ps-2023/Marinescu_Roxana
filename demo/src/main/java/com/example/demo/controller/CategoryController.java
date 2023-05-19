package com.example.demo.controller;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public List<CategoryDTO> findAllCateg() {
        return categoryService.findAll();
    }

    @GetMapping("/{name}")
    public ResponseEntity findCategByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findByName(name));
    }

    @GetMapping("/find/{name}")
    public CategoryDTO findCategByName1(@PathVariable String name) {
        System.out.println(categoryService.findAllByName(name).get().getName());
        return categoryService.findByName(name);
    }

    @GetMapping("/find")
    public ResponseEntity findByNameReqParam(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findByName(name));
    }

    @RequestMapping(value="/delete")
    public Category delete(@RequestBody Category category) {
        System.out.println(category);
        return categoryService.deleteCategory(category);
    }

    @RequestMapping(value="/addcategory")
    public Category addCateg(@RequestBody Category category) {
        if(category.getName().equals("")){
            System.out.println("Nume invalid de categorie");
            return null;
        }
        return categoryService.createCategory(category);
    }

    @RequestMapping(value="/update")
    public Category updateCateg(@RequestBody Category category) {
        System.out.println(category);
        Category c = categoryService.findById(category.getId());
        if(category.getName()==null) category.setName(c.getName());
        if(category.getProducts()==null) category.setProducts(c.getProducts());
        return categoryService.updateCategory(category);
    }

    @PostMapping("/create")
    public Category createCateg(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public CategoryDTO findById(@RequestParam int id) {
        return categoryService.findByIdDTO(id);
    }
}
