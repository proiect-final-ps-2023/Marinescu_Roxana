package com.example.demo.mapper;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.model.Cart;
import com.example.demo.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public static OrderDTO mapModelToDto(Order order){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setName(order.getName());
        orderDTO.setEmail(order.getEmail());
        orderDTO.setAddress(order.getAddress());
        orderDTO.setCity(order.getCity());
        orderDTO.setState(order.getState());
        orderDTO.setZip(order.getZip());
        orderDTO.setCart_id(order.getCart().getId());

        return orderDTO;
    }

    public static Order mapDtoToModel(OrderDTO orderDTO){
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setName(orderDTO.getName());
        order.setEmail(orderDTO.getEmail());
        order.setAddress(orderDTO.getAddress());
        order.setState(orderDTO.getState());
        order.setCity(orderDTO.getCity());
        order.setZip(orderDTO.getZip());

        Cart cart = new Cart();
        cart.setId(orderDTO.getCart_id());
        order.setCart(cart);

        return order;
    }
}
