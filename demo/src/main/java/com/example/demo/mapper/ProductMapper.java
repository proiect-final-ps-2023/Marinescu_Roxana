package com.example.demo.mapper;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static ProductDTO mapModelToDto(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImgUrl(product.getImgUrl());
        productDTO.setCategory_id(product.getCategory().getId());

        return productDTO;
    }

    public static Product mapDtoToModel(ProductDTO productDTO){
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDescription(productDTO.getDescription());
        product.setImgUrl(productDTO.getImgUrl());
        Category category = new Category();
        category.setId(productDTO.getCategory_id());
        product.setCategory(category);

        return product;
    }

}
