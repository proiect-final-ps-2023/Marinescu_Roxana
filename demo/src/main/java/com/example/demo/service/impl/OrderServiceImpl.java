package com.example.demo.service.impl;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.mapper.CartMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.model.Cart;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepo;
import com.example.demo.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    private OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepo orderRepo){
        this.orderRepo=orderRepo;
        orderMapper=new OrderMapper();
    }

    @Override
    public Order updateOrder(Order order) {
        Order updateOrder = order;
        orderRepo.save(updateOrder);
        return updateOrder;
    }

    @Override
    public Order deleteOrder(Order order) {
        Order deleteOrder = orderRepo.findById(order.getId()).get();
        orderRepo.delete(deleteOrder);
        return order;
    }

    @Override
    public Order createOrder(Order order) {
        orderRepo.save(order);
        return order;
    }

    @Override
    public OrderDTO findbyId(int id) {
        final Order order = orderRepo.findById(id).orElseThrow(()
                ->
        {
            throw new EntityNotFoundException("Nu se poate gasi cartul");
        });
        return orderMapper.mapModelToDto(order);
    }

    @Override
    public OrderDTO findbyCartId(int id) {
        final Order o = orderRepo.findByCartId(id);
        return orderMapper.mapModelToDto(o);
    }
}
