package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
    Collection<Product> findAllByName(String name);
    Product findByName(String name);
    Collection<Product> findAllByCategoryId(int category_id);
    Collection<Product> findAllByCartId(int id);

    Collection<Product> findAllByWishlistId(int id);
}
