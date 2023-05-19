package com.example.demo.DTO;
import com.example.demo.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private int id;
    private String name;
    private float price;
    private String description;
    private String imgUrl;
    private int category_id;
}
