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
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private User user;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.PERSIST,fetch = FetchType.LAZY)
    private Order order;
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "cart")
    private List<Product> product;

}
