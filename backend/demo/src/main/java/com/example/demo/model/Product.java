package com.example.demo.model;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    public Product() {} // REQUIRED

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // getters + setters
      public Long getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
}


