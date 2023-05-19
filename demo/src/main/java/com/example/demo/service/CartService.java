package com.example.demo.service;

import com.example.demo.DTO.CartDTO;
import com.example.demo.model.Cart;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface CartService {
    public Cart updateCart(Cart cart);
    public Cart deleteCart(Cart cart);
    public Cart createCart(Cart cart);

    List<CartDTO> findAll();
    CartDTO findById(int id);
    CartDTO findByUserId(int id);

    Optional<Cart> findAllById(int id);

    List<CartDTO> findAllByProductId(int id);
}
