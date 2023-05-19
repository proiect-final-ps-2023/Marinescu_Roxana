package com.example.demo.model;

import com.example.demo.enums.Auth;
import com.example.demo.repository.*;
import com.example.demo.service.impl.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String mail;
    private String password;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Cart cart;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Wishlist wishlist;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private LoginLogout loginLogout;
    private Auth auth;

    public void deleteCateg(CategoryRepo categoryRepo,Category category){
        if(verifyAuth()){
            CategoryServiceImpl cs = new CategoryServiceImpl(categoryRepo);
            cs.deleteCategory(category);
        }
    }
    public void deleteProd(ProductRepo productRepo, Product product){
        if(verifyAuth()){
            ProductServiceImpl ps = new ProductServiceImpl(productRepo);
            ps.deleteProd(product);
        }
    }

    public void deleteUser(UserRepo userRepo, User user){
        if(verifyAuth()){
            UserServiceImpl us = new UserServiceImpl(userRepo);
            us.deleteUser(user);
        }
    }

    public void updateUser(UserRepo userRepo, User user){
        if(verifyAuth()){
            UserServiceImpl us = new UserServiceImpl(userRepo);
            us.updateUser(user);
        }
    }

    public void createUser(UserRepo userRepo, User user){
        if(verifyAuth()){
            UserServiceImpl us = new UserServiceImpl(userRepo);
            us.createUser(user);
        }
    }

    public void createProd(ProductRepo productRepo, Product product){
        if(verifyAuth()){
            ProductServiceImpl ps = new ProductServiceImpl(productRepo);
            ps.createProd(product);
        }
    }
    public void createCateg(CategoryRepo categoryRepo,Category category){
        if(verifyAuth()){
            CategoryServiceImpl cs = new CategoryServiceImpl(categoryRepo);
            cs.createCategory(category);
        }
    }
    public void updateCateg(CategoryRepo categoryRepo,Category category){
        if(verifyAuth()){
            CategoryServiceImpl cs = new CategoryServiceImpl(categoryRepo);
            cs.updateCategory(category);
        }
    }
    public void updateProd(ProductRepo productRepo, Product product){
        if(verifyAuth()){
            ProductServiceImpl ps = new ProductServiceImpl(productRepo);
            ps.updateProd(product);
        }
    }

    public void createWishlist(WishlistRepo wishlistRepo, Wishlist wishlist){
        if(verifyAuth()){
            WishlistServiceImpl cs = new WishlistServiceImpl(wishlistRepo);
            cs.createWishlist(wishlist);
        }
    }
    public void deleteWishlist(WishlistRepo wishlistRepo, Wishlist wishlist){
        if(verifyAuth()){
            WishlistServiceImpl cs = new WishlistServiceImpl(wishlistRepo);
            cs.deleteWishlist(wishlist);
        }
    }
    public void updateWishlist(WishlistRepo wishlistRepo, Wishlist wishlist){
        if(verifyAuth()){
            WishlistServiceImpl cs = new WishlistServiceImpl(wishlistRepo);
            cs.updateWishlist(wishlist);
        }
    }

    public void updateCart(CartRepo cartRepo, Cart cart){
        if(verifyAuth()){
            CartServiceImpl cs = new CartServiceImpl(cartRepo);
            cs.updateCart(cart);
        }
    }
    public void deleteCart(CartRepo cartRepo, Cart cart){
        if(verifyAuth()){
            CartServiceImpl cs = new CartServiceImpl(cartRepo);
            cs.deleteCart(cart);
        }
    }
    public void createCart(CartRepo cartRepo, Cart cart){
        if(verifyAuth()){
            CartServiceImpl cs = new CartServiceImpl(cartRepo);
            cs.createCart(cart);
        }
    }

    public void updateOrder(OrderRepo orderRepo, Order order){
        if(verifyAuth()){
            OrderServiceImpl cs = new OrderServiceImpl(orderRepo);
            cs.updateOrder(order);
        }
    }
    public void deleteOrder(OrderRepo orderRepo, Order order){
        if(verifyAuth()){
            OrderServiceImpl cs = new OrderServiceImpl(orderRepo);
            cs.deleteOrder(order);
        }
    }
    public void createOrder(OrderRepo orderRepo, Order order){
        if(verifyAuth()){
            OrderServiceImpl cs = new OrderServiceImpl(orderRepo);
            cs.createOrder(order);
        }
    }

    public void createLog(LogRepo logRepo, LoginLogout loginLogout){
        if(verifyAuth()){
            LogServiceImpl cs = new LogServiceImpl(logRepo);
            cs.createLoginLogout(loginLogout);
        }
    }

    public void deleteLog(LogRepo logRepo, LoginLogout loginLogout){
        if(verifyAuth()){
            LogServiceImpl cs = new LogServiceImpl(logRepo);
            cs.deleteLoginLogout(loginLogout);
        }
    }

    public void updateLog(LogRepo logRepo, LoginLogout loginLogout){
        if(verifyAuth()){
            LogServiceImpl cs = new LogServiceImpl(logRepo);
            cs.updateLoginLogout(loginLogout);
        }
    }

    public boolean verifyAuth(){
        if(this.auth == Auth.ADMIN){
            return true;
        }
        return false;
    }

}
