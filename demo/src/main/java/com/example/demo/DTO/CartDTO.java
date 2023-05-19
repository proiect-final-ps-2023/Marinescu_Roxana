package com.example.demo.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartDTO {
    private int id;
    private int user_id;
    private int order_id;
}
