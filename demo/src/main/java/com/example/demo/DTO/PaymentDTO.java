package com.example.demo.DTO;

import com.example.demo.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
    private int id;
    private String name;
    private String card;
    private String month;
    private String year;
    private String cvv;
    private int order_id;
}
