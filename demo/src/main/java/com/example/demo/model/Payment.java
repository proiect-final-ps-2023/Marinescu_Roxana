package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String card;
    private String month;
    private String year;
    private String cvv;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Order order;
}
