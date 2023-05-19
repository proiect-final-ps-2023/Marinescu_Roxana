package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private float price;
    private String description;
    private String imgUrl;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    private Category category;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Cart> cart;
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Wishlist> wishlist;
}
