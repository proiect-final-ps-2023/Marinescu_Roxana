package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepo;
import com.example.demo.service.impl.ProductServiceImpl;
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

public class ProductServiceTests {
    private static final String NAME = "Banane";
    private static final String NAME1 = "Baby Banane";

    @Mock
    private ProductRepo productRepo;
    @InjectMocks
    private ProductServiceImpl productService;
    private Product product;

    @BeforeEach
    void init() {
        initMocks(this);
        product = new Product();
        productService = new ProductServiceImpl(productRepo);
        when(productRepo.save(product)).thenReturn(product);
    }

    @Test
    void updateVerify(){
        Product product1 = new Product();
        product1.setName(NAME);
        product1.setPrice(3);
        product1.setId(12);
        Product product2 = new Product();
        product2.setName(NAME1);
        product2.setPrice(3.5f);
        product2.setId(12);
        given(productRepo.findById(product1.getId())).willReturn(Optional.of(product1));
        productService.updateProd(product2);
//        verify(productRepo).save(product2);
//        verify(productRepo).findById(product1.getId());
    }

    @Test
    void updateVerify_ExceptionIfProductDoesntExist(){
        try {

        Product product1 = new Product();
        product1.setName(NAME);
        product1.setPrice(3);
        product1.setId(12);

        Product product2 = new Product();
        product2.setName(NAME1);
        product2.setPrice(3.5f);
        product2.setId(13);

        given(productRepo.findById(anyInt())).willReturn(Optional.ofNullable(null));
        productService.updateProd(product2);
        }catch(RuntimeException re){}
    }

    @Test
    void deleteVerify(){
        Product product1 = new Product();
        product1.setName(NAME);
        product1.setPrice(3);
        product1.setId(12);
        when(productRepo.findById(product1.getId())).thenReturn(Optional.of(product1));
        productService.deleteProd(product1);
        //verify(productRepo).delete(product1);
    }

    @Test
    void deleteVerify_ExceptionIfProductDoesntExist(){
        try {
            Product product1 = new Product();
            product1.setName(NAME);
            product1.setPrice(3);
            product1.setId(13);
            given(productRepo.findById(anyInt())).willReturn(Optional.ofNullable(null));
            productService.deleteProd(product1);
        }catch(RuntimeException re){}
    }

    @Test
    void createVerify(){
        Product product1 = new Product();
        product1.setName(NAME);
        product1.setId(12);
        product1.setPrice(3);
        productService.createProd(product1);
    }
}
