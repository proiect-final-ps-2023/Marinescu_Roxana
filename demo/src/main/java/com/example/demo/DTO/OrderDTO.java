package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private int id;
    private String name;
    private String email;
    private String address;
    private String state;
    private String city;
    private String zip;
    private int cart_id;
}
