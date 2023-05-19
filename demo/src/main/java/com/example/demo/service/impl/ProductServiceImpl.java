package com.example.demo.service.impl;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepo;
import com.example.demo.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    private ProductMapper productMapper;

    public ProductServiceImpl(ProductRepo productRepo){
        this.productRepo = productRepo;
        productMapper = new ProductMapper();
    }

    @Override
    public Product updateProd(Product product) {

        Product updateProd = productRepo.findById(product.getId()).get();
        updateProd.setName(product.getName());
        updateProd.setDescription(product.getDescription());
        updateProd.setPrice(product.getPrice());
        updateProd.setCart(product.getCart());
        updateProd.setId(product.getId());
        updateProd.setCategory(product.getCategory());
        updateProd.setWishlist(product.getWishlist());

        productRepo.save(updateProd);
        return updateProd;
    }

    @Override
    public Product deleteProd(Product product) {
        Product deleteProd = productRepo.findById(product.getId()).get();
        productRepo.delete(deleteProd);
        return product;
    }

    @Override
    public Product createProd(Product product) {
        productRepo.save(product);
        return product;
    }

    @Override
    public ProductDTO findByIdDTO(int id) {
        final Product product = productRepo.findById(id).orElseThrow(()
                ->
        {
            throw new EntityNotFoundException("Nu se poate gasi produsul cu Id-ul:" + id);
        });
        return productMapper.mapModelToDto(product);
    }

    @Override
    public Product findbyId(int id) {
        return productRepo.findById(id).get();
    }

    @Override
    public List<ProductDTO> findAll() {
        return productRepo.findAll()
                .stream().map(
                        ProductMapper::mapModelToDto
                ).collect(Collectors.toList());
    }

    @Override
    public ProductDTO findByName(String name) {
        final Product product = productRepo.findByName(name);
        return ProductMapper.mapModelToDto(product);
    }

    @Override
    public List<ProductDTO> findAllByName(String name) {
        return productRepo.findAllByName(name)
                .stream().map(
                        ProductMapper::mapModelToDto
                ).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findAllByCategId(int category_id) {
        return productRepo.findAllByCategoryId(category_id)
                .stream().map(
                        ProductMapper::mapModelToDto
                ).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findAllByCartId(int id) {
        return productRepo.findAllByCartId(id)
                .stream().map(
                        ProductMapper::mapModelToDto
                ).collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findAllByWishlistId(int id) {
        return productRepo.findAllByWishlistId(id)
                .stream().map(
                        ProductMapper::mapModelToDto
                ).collect(Collectors.toList());
    }
}
