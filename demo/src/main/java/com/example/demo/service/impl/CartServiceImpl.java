package com.example.demo.service.impl;

import com.example.demo.DTO.CartDTO;
import com.example.demo.mapper.CartMapper;
import com.example.demo.model.Cart;
import com.example.demo.repository.CartRepo;
import com.example.demo.service.CartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepo cartRepo;
    private CartMapper cartMapper;

    public CartServiceImpl(CartRepo cartRepo){
        this.cartRepo = cartRepo;
        cartMapper = new CartMapper();
    }

    @Override
    public Cart updateCart(Cart cart) {
        Cart updateCart = cart;
        cartRepo.save(updateCart);
        return updateCart;
    }

    @Override
    public Cart deleteCart(Cart cart) {
        Cart deleteCart = cartRepo.findById(cart.getId()).get();
        cartRepo.delete(deleteCart);
        return cart;
    }

    @Override
    public Cart createCart(Cart cart) {
        cartRepo.save(cart);
        return cart;
    }

    @Override
    public List<CartDTO> findAll() {
        return cartRepo.findAll()
                .stream().map(
                        CartMapper::mapModelToDto
                ).collect(Collectors.toList());
    }

    @Override
    public CartDTO findById(int id) {
        final Cart cart = cartRepo.findById(id).orElseThrow(()
                ->
        {
            throw new EntityNotFoundException("Nu se poate gasi cartul");
        });
        return cartMapper.mapModelToDto(cart);
    }

    @Override
    public Optional<Cart> findAllById(int id) {
        return cartRepo.findAllById(id);
    }

    @Override
    public List<CartDTO> findAllByProductId(int id) {
        return cartRepo.findAllByProductId(id).stream().map(CartMapper::mapModelToDto).collect(Collectors.toList());
    }

    @Override
    public CartDTO findByUserId(int id){
        final Cart cart = cartRepo.findByUserId(id);
        return cartMapper.mapModelToDto(cart);
    }

}
