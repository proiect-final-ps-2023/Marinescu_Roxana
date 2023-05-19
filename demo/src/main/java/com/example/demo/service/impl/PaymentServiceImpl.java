package com.example.demo.service.impl;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.OrderDTO;
import com.example.demo.DTO.PaymentDTO;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.mapper.OrderMapper;
import com.example.demo.mapper.PaymentMapper;
import com.example.demo.model.Order;
import com.example.demo.model.Payment;
import com.example.demo.repository.OrderRepo;
import com.example.demo.repository.PaymentRepo;
import com.example.demo.service.PaymentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepo paymentRepo;
    private PaymentMapper paymentMapper;

    public PaymentServiceImpl(PaymentRepo paymentRepo){
        this.paymentRepo=paymentRepo;
        paymentMapper=new PaymentMapper();
    }

    @Override
    public Payment updatePayment(Payment payment) {
        Payment updatePayment = payment;
        paymentRepo.save(updatePayment);
        return updatePayment;
    }

    @Override
    public Payment deletePayment(Payment payment) {
        Payment deletePayment = paymentRepo.findById(payment.getId()).get();
        paymentRepo.delete(deletePayment);
        return payment;
    }

    @Override
    public Payment createPayment(Payment payment) {
        paymentRepo.save(payment);
        return payment;
    }

    @Override
    public PaymentDTO findbyId(int id) {
        final Payment p = paymentRepo.findById(id).orElseThrow(()
                ->
        {
            throw new EntityNotFoundException("Nu se poate gasi payment");
        });
        return paymentMapper.mapModelToDto(p);
    }

    @Override
    public PaymentDTO findbyOrderId(int id) {
        final Payment p = paymentRepo.findByOrderId(id);
        return paymentMapper.mapModelToDto(p);
    }
    @Override
    public List<PaymentDTO> findAll() {
        return paymentRepo.findAll()
                .stream().map(
                        PaymentMapper::mapModelToDto
                ).collect(Collectors.toList());
    }
}
