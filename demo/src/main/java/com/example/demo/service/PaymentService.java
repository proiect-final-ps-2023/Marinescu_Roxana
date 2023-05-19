package com.example.demo.service;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.OrderDTO;
import com.example.demo.DTO.PaymentDTO;
import com.example.demo.model.Order;
import com.example.demo.model.Payment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PaymentService {
    public Payment updatePayment(Payment payment);
    public Payment deletePayment(Payment payment);
    public Payment createPayment(Payment payment);
    PaymentDTO findbyId(int id);
    PaymentDTO findbyOrderId(int id);
    List<PaymentDTO> findAll();
}
