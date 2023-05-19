package com.example.demo.mapper;

import com.example.demo.DTO.CartDTO;
import com.example.demo.model.Cart;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class CartMapper {
    public static CartDTO mapModelToDto(Cart cart){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setUser_id(cart.getUser().getId());
        cartDTO.setOrder_id(cart.getOrder().getId());
        return cartDTO;
    }

    public static Cart mapDtoToModel(CartDTO cartDTO){
        Cart cart = new Cart();
        cart.setId(cartDTO.getId());
        User user = new User();
        user.setId(cartDTO.getUser_id());
        cart.setUser(user);
        Order order = new Order();
        order.setId(cartDTO.getOrder_id());
        cart.setOrder(order);

        return cart;
    }
}
