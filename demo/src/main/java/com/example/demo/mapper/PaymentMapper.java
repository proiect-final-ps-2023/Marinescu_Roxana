package com.example.demo.mapper;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.DTO.PaymentDTO;
import com.example.demo.model.Order;
import com.example.demo.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public static PaymentDTO mapModelToDto(Payment payment){
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(payment.getId());
        paymentDTO.setName(payment.getName());
        paymentDTO.setCard(payment.getCard());
        paymentDTO.setCvv(payment.getCvv());
        paymentDTO.setMonth(payment.getMonth());
        paymentDTO.setYear(payment.getYear());
        paymentDTO.setOrder_id(payment.getOrder().getId());
        return paymentDTO;
    }

    public static Payment mapDtoToModel(PaymentDTO paymentDTO){
        Payment payment = new Payment();
        payment.setCard(paymentDTO.getCard());
        payment.setCvv(paymentDTO.getCvv());
        payment.setYear(paymentDTO.getYear());
        payment.setMonth(paymentDTO.getMonth());
        payment.setName(paymentDTO.getName());
        payment.setId(paymentDTO.getId());

        Order order = new Order();
        order.setId(paymentDTO.getOrder_id());
        payment.setOrder(order);
        return payment;
    }
}
