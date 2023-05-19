package com.example.demo.service.impl;

import com.example.demo.DTO.WishlistDTO;
import com.example.demo.mapper.CartMapper;
import com.example.demo.mapper.WishlistMapper;
import com.example.demo.model.Cart;
import com.example.demo.model.Wishlist;
import com.example.demo.repository.WishlistRepo;
import com.example.demo.service.WishlistService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WishlistServiceImpl implements WishlistService {
    @Autowired
    private WishlistRepo wishlistRepo;
    private WishlistMapper wishlistMapper;

    public WishlistServiceImpl(WishlistRepo wishlistRepo){
        this.wishlistRepo = wishlistRepo;
        wishlistMapper = new WishlistMapper();
    }

    @Override
    public Wishlist updateWishlist(Wishlist wishlist) {
        Wishlist updateWishlist = wishlist;
        wishlistRepo.save(updateWishlist);
        return updateWishlist;
    }

    @Override
    public Wishlist deleteWishlist(Wishlist wishlist) {
        Wishlist deleteWishlist = wishlistRepo.findById(wishlist.getId()).get();
        wishlistRepo.delete(deleteWishlist);
        return wishlist;
    }

    @Override
    public Wishlist createWishlist(Wishlist wishlist) {
        wishlistRepo.save(wishlist);
        return wishlist;
    }

    @Override
    public Wishlist findbyId(int id) {
        return wishlistRepo.findById(id).get();
    }

    @Override
    public List<WishlistDTO> findAll() {
        return wishlistRepo.findAll()
                .stream().map(
                        WishlistMapper::mapModelToDto
                ).collect(Collectors.toList());
    }

    @Override
    public WishlistDTO findById(int id) {
        final Wishlist wishlist = wishlistRepo.findById(id).orElseThrow(()
                ->
        {
            throw new EntityNotFoundException("Nu se poate gasi wishlistul");
        });
        return WishlistMapper.mapModelToDto(wishlist);
    }

    @Override
    public Optional<Wishlist> findAllById(int id) {
        return wishlistRepo.findAllById(id);
    }

    @Override
    public WishlistDTO findByUserId(int u) {
        final Wishlist wishlist = wishlistRepo.findByUserId(u);
        return wishlistMapper.mapModelToDto(wishlist);
    }

    @Override
    public List<WishlistDTO> findAllByProductId(int id) {
        return wishlistRepo.findAllByProductId(id).stream().map(WishlistMapper::mapModelToDto).collect(Collectors.toList());
    }
}
