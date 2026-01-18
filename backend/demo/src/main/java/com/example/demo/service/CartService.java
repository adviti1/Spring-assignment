package com.example.demo.service;

import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {

    private final Map<Long, CartItem> cart = new HashMap<>();
    private Long nextId = 1L; // unique cart item ID generator

    public Collection<CartItem> getCart() {
        return cart.values();
    }

    public void add(Product product) {
       

    // Check if product already exists in cart
    Optional<CartItem> existing = cart.values().stream()
            .filter(item -> item.getProduct().getId().equals(product.getId()))
            .findFirst();

    if (existing.isPresent()) {
        existing.get().setQuantity(existing.get().getQuantity() + 1);
    } else {
        CartItem newItem = new CartItem(nextId++, product, 1); // ✅ set ID here
        newItem.setId(nextId - 1); // make sure ID is set
        cart.put(newItem.getId(), newItem);
    }
}
    public void update(Long cartItemId, int qty) {
        CartItem item = cart.get(cartItemId);
        if (item != null) {
            item.setQuantity(qty);
        }
    }

    public void delete(Long cartItemId) {
        cart.remove(cartItemId);
    }

    public void checkout() {
        cart.clear();
    }
}
