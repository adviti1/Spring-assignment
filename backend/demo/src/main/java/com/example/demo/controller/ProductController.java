package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import com.example.demo.dto.ProductDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping
public List<ProductDTO> getProducts() {
    return productService.getAllProducts();
}

  
}

