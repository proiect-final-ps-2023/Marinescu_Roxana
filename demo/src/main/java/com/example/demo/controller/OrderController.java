package com.example.demo.controller;

import com.example.demo.DTO.*;
import com.example.demo.mapper.*;
import com.example.demo.model.Cart;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repository.CartRepo;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    private OrderMapper orderMapper;
    private CartService cartService;
    private CartRepo cartRepo;
    private UserService userService;
    private ProductService productService;
    private UserMapper userMapper;
    private CartMapper cartMapper;
    private ProductMapper productMapper;
    private CategoryService categoryService;
    private CategoryMapper categoryMapper;
    private WishlistService wishlistService;
    private WishlistMapper wishlistMapper;

    public OrderController(CategoryService categoryService,CategoryMapper categoryMapper, CartService cartService,CartMapper cartMapper,CartRepo cartRepo, UserService userService, ProductService productService, UserMapper userMapper, ProductMapper productMapper,
                          WishlistMapper wishlistMapper,WishlistService wishlistService,OrderMapper orderMapper,OrderService orderService) {
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
        this.userMapper = userMapper;
        this.productMapper = productMapper;
        this.cartRepo = cartRepo;
        this.cartMapper = cartMapper;
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
        this.wishlistMapper=wishlistMapper;
        this.wishlistService=wishlistService;
        this.orderService=orderService;
        this.orderMapper=orderMapper;
    }


    @RequestMapping(value="/add")
    public Order add(@RequestBody Order order)
    {
        if(order.getName().equals("")){
            System.out.println("Trebuie introdus un nume!");
            return null;}
        if(order.getCity().equals("")){
            System.out.println("Trebuie introdus un oras!");
            return null;}
        if(order.getEmail().equals("")){
            System.out.println("Trebuie introdus un email!");
            return null;}
        if(order.getAddress().equals("")){
            System.out.println("Trebuie introdusa o adresa");
            return null;}
        if(order.getZip().equals("")){
            System.out.println("Trebuie introdus un cod postal");
            return null;}
        if(order.getState().equals("")){
            System.out.println("Trebuie introdusa o tara");
            return null;}

        OrderDTO orderDTO = (orderService.findbyCartId(order.getId()));
        Cart cart = cartMapper.mapDtoToModel(cartService.findById(orderDTO.getCart_id()));
        CartDTO cart1 = (cartService.findById(orderDTO.getCart_id()));
        List<ProductDTO> productDTOS = productService.findAllByCartId(cart1.getId());
        List<Product> ps = productDTOS.stream().map(ProductMapper::mapDtoToModel).collect(Collectors.toList());
        cart.setProduct(ps);
        order.setCart(cart);
        order.setId(orderDTO.getId());
        cart1.setOrder_id(order.getId());
        cart.setOrder(order);
        cartService.updateCart(cart);
        return orderService.createOrder(order);
    }

}
