package com.example.demo.controller;

import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
public class CartController {

    private final CartService cartService;
    private final ProductService productService;

    public CartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping
    public Object getCart() {
        return cartService.getCart();
    }

    @PostMapping("/add/{id}")
    public void add(@PathVariable Long id) {
        cartService.add(productService.getById(id));
    }

    @PutMapping("/update/{id}")
    public void update(@PathVariable Long id, @RequestParam int qty) {
        cartService.update(id, qty);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        cartService.delete(id);
    }

    @PostMapping("/checkout")
    public void checkout() {
        cartService.checkout();
    }
}
