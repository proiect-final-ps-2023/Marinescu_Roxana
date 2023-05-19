package com.example.demo.service;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public interface ProductService {
    public Product updateProd(Product product);
    public Product deleteProd(Product product);
    public Product createProd(Product product);

    ProductDTO findByIdDTO(int id);
    Product findbyId(int id);
    List<ProductDTO> findAll();
    ProductDTO findByName(String name);
    List<ProductDTO> findAllByName(String name);

    List<ProductDTO> findAllByCategId(int category_id);

    List<ProductDTO> findAllByCartId(int id);

    List<ProductDTO> findAllByWishlistId(int id);
}
