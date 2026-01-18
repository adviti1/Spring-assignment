package com.example.demo.controller;

import com.example.demo.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    @GetMapping
    public List<Product> getProducts() {
        return List.of(
            new Product(1L, "Perfume", 999),
            new Product(2L, "Watch", 1999),
            new Product(3L, "Shoes", 2999)
        );
    }
}
