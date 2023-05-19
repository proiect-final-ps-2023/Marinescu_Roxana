package com.example.demo.service;

import com.example.demo.DTO.WishlistDTO;
import com.example.demo.model.Wishlist;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
@Component
public interface WishlistService {
    public Wishlist updateWishlist(Wishlist wishlist);
    public Wishlist deleteWishlist(Wishlist wishlist);
    public Wishlist createWishlist(Wishlist wishlist);

    Wishlist findbyId(int id);
    List<WishlistDTO> findAll();
    WishlistDTO findById(int id);
    Optional<Wishlist> findAllById(int id);

    WishlistDTO findByUserId(int u);

    List<WishlistDTO> findAllByProductId(int id);
}
