package com.example.demo.model;

import java.util.Comparator;

public class ProductComp implements java.util.Comparator<Product> {
    @Override
    public Comparator<Product> reversed() {
        return Comparator.super.reversed();
    }

    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getPrice()-o2.getPrice());
    }

}
