package com.example.demo.service;

import com.example.demo.model.Product;
import org.springframework.stereotype.Service;
import com.example.demo.repository.ProductRepository;
import com.example.demo.dto.ProductDTO;




import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
    return productRepository.findAll()
            .stream()
            .map(this::toDTO)
            .toList();
}
   

    private ProductDTO toDTO(Product product) {
    return new ProductDTO(
            product.getId(),
            product.getName(),
            product.getPrice()
    );
}
    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}
