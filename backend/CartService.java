package com.example.cart.service;

import com.example.cart.model.CartItem;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {

    private final Map<Long, CartItem> cart = new HashMap<>();

    public Collection<CartItem> getCartItems() {
        return cart.values();
    }

    public void addToCart(CartItem item) {
        cart.put(item.getProductId(), item);
    }

    public void updateQuantity(Long id, int qty) {
        if (cart.containsKey(id)) {
            cart.get(id).setQuantity(qty);
        }
    }

    public void removeItem(Long id) {
        cart.remove(id);
    }

    public void clearCart() {
        cart.clear();
    }
}
