package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final List<Product> products = List.of(
        new Product(1L, "Perfume", 999),
        new Product(2L, "Watch", 1999),
        new Product(3L, "Shoes", 2999)
    );

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
