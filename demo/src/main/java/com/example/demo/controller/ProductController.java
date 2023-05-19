package com.example.demo.controller;
import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.*;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    private ProductMapper productMapper;
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;

    public ProductController(ProductService productService, ProductMapper productMapper, CategoryService categoryService,CategoryMapper categoryMapper) {
        this.productService = productService;
        this.productMapper=productMapper;
        this.categoryMapper=categoryMapper;
        this.categoryService=categoryService;
    }

    @RequestMapping("/sortByPriceAsc")
    public List<Product>sortA(){
        List<Product> prods= productService.findAll().stream().map(ProductMapper::mapDtoToModel).collect(Collectors.toList());
        Collections.sort(prods,new ProductComp());
        return prods;
    }

    @RequestMapping("/sortByPriceDesc")
    public List<Product>sortD(){
        List<Product> prods= productService.findAll().stream().map(ProductMapper::mapDtoToModel).collect(Collectors.toList());
        Collections.sort(prods,new ProductComp().reversed());
        return prods;
    }

    @GetMapping()
    public List<ProductDTO> findAllProd() {
        return productService.findAll();
    }

    @RequestMapping(value="/byCategId", method = RequestMethod.GET)
    public List<ProductDTO> findAllByCategId(@RequestParam int id) {
        return productService.findAllByCategId(id);
    }

    @GetMapping("/{name}")
    public ResponseEntity findProdByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByName(name));
    }

    @RequestMapping(value="/findByName",method = RequestMethod.GET)
    public List<ProductDTO> findProdByName1(@RequestParam String result) {
        System.out.println(result);
        return productService.findAllByName(result);
    }

    @GetMapping("/find")
    public ResponseEntity findByNameReqParam(@RequestParam String name) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findByName(name));
    }
    @RequestMapping(value="/delete")
    public Product delete(@RequestBody Product product) {
        System.out.println(product);
        return productService.deleteProd(product);
    }

    @RequestMapping(value="/addproduct")
    public Product addProd(@RequestBody ProductDTO product) {
        CategoryDTO category = categoryMapper.mapModelToDto(categoryService.findById(product.getCategory_id()));
        Category category1 = categoryMapper.mapDtoToModel(category);
        List<ProductDTO> p = productService.findAllByCategId(category.getId());
        Product product1 = productMapper.mapDtoToModel(product);
        product1.setCategory(category1);
        p.add(product);
        List<Product> p1 = new ArrayList<Product>();
        for(ProductDTO i: p){
            p1.add(productMapper.mapDtoToModel(i));
        }

        if(product.getPrice()<=0){
            System.out.println("Pretul nu poate fi mai mic decat 0");
            return null;
        }
        if(product.getName().equals("")){
            System.out.println("Nume invalid de produs");
            return null;
        }
        if(product.getCategory_id()==0){
            System.out.println("Nume invalid de produs");
            return null;
        }

        category1.setProducts(p1);
        categoryService.updateCategory(category1);

        return product1;
    }

    @RequestMapping(value="/update")
    public Product updateProd(@RequestBody Product product) {
        System.out.println(product);
        Product p = productService.findbyId(product.getId());
        if(product.getName()==null) product.setName(p.getName());
        if(product.getPrice()==0) product.setPrice(p.getPrice());
        if(product.getDescription()==null) product.setDescription(p.getDescription());
        if(product.getCategory()==null) product.setCategory(p.getCategory());
        if(product.getImgUrl()==null) product.setImgUrl(p.getImgUrl());
        if(product.getCart()==null) product.setCart(p.getCart());
        if(product.getWishlist()==null) product.setWishlist(p.getWishlist());
        Product pr = productService.updateProd(product);
        System.out.println(pr.getName());
        return pr;
    }

    @PostMapping("/create")
    public Product createProd(@RequestBody Product product) {
        return productService.createProd(product);
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public ProductDTO findById(@RequestParam int id) {
        return productService.findByIdDTO(id);
    }
}
