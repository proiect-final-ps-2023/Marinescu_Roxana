package com.example.demo.repository;

import com.example.demo.model.Cart;
import com.example.demo.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Repository
public interface WishlistRepo extends JpaRepository<Wishlist,Integer> {
    Optional<Wishlist> findAllById(int id);

    Wishlist findByUserId(int u);

    List<Wishlist> findAllByProductId(int id);

}
