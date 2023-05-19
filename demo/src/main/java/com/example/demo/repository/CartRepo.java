package com.example.demo.repository;

import com.example.demo.DTO.CartDTO;
import com.example.demo.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface CartRepo extends JpaRepository<Cart,Integer> {
    Optional<Cart> findAllById(int id);
    Cart findByUserId(int id);
    List<Cart> findAllByProductId(int id);
}
