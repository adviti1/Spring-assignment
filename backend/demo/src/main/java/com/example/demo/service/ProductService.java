package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;
import com.example.demo.repository.ProductRepository;


import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
