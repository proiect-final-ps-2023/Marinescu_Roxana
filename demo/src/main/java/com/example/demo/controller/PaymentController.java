package com.example.demo.controller;

import com.example.demo.DTO.CartDTO;
import com.example.demo.DTO.OrderDTO;
import com.example.demo.DTO.PaymentDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.mapper.*;
import com.example.demo.model.Cart;
import com.example.demo.model.Order;
import com.example.demo.model.Payment;
import com.example.demo.model.Product;
import com.example.demo.repository.CartRepo;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payment")
public class PaymentController {
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
    private PaymentMapper paymentMapper;
    private PaymentService paymentService;

    public PaymentController(OrderService orderService, OrderMapper orderMapper, CartService cartService, CartRepo cartRepo, UserService userService, ProductService productService, UserMapper userMapper, CartMapper cartMapper, ProductMapper productMapper, CategoryService categoryService, CategoryMapper categoryMapper, WishlistService wishlistService, WishlistMapper wishlistMapper, PaymentMapper paymentMapper, PaymentService paymentService) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.cartService = cartService;
        this.cartRepo = cartRepo;
        this.userService = userService;
        this.productService = productService;
        this.userMapper = userMapper;
        this.cartMapper = cartMapper;
        this.productMapper = productMapper;
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
        this.wishlistService = wishlistService;
        this.wishlistMapper = wishlistMapper;
        this.paymentMapper = paymentMapper;
        this.paymentService = paymentService;
    }

    @RequestMapping(value="/add")
    public Payment add(@RequestBody Payment payment) throws UnsupportedEncodingException {
        if(payment.getName().equals("")){
            System.out.println("Trebuie introdus un nume!");
            return null;}
        if(payment.getCard().equals("")){
            System.out.println("Trebuie introdus un card!");
            return null;}
        if(payment.getCvv().equals("")){
            System.out.println("Trebuie introdusa un cod cvv");
            return null;}
        if(payment.getMonth().equals("")){
            System.out.println("Trebuie introdusa o luna");
            return null;}
        if(payment.getYear().equals("")){
            System.out.println("Trebuie introdus un an");
            return null;}

        OrderDTO orderDTO = (orderService.findbyCartId(payment.getId()));
        Order order = orderMapper.mapDtoToModel(orderService.findbyCartId(payment.getId()));
        Cart cart = cartMapper.mapDtoToModel(cartService.findById(orderDTO.getCart_id()));
        CartDTO cart1 = (cartService.findById(orderDTO.getCart_id()));
        Payment payment1 = paymentMapper.mapDtoToModel(paymentService.findbyId(order.getPayment().getId()));
        order.setPayment(payment1);
        orderDTO.setPayment_id(payment1.getId());
        order.setCart(cart);
        payment1.setOrder(order);
        payment1.setName(payment.getName());
        payment1.setYear(payment.getYear());
        payment1.setMonth(payment.getMonth());
        byte[] encodedBytes = Base64.getEncoder().encode(payment.getCvv().getBytes("UTF-8"));
        payment1.setCvv(new String(encodedBytes));
        payment1.setCard(payment.getCard());
        payment1.setId(order.getPayment().getId());
        cart1.setOrder_id(order.getId());
        cart.setOrder(order);
        cartService.updateCart(cart);
        orderService.updateOrder(order);
        return paymentService.createPayment(payment1);
    }
}
