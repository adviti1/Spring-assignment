package com.example.cart.controller;

import com.example.cart.model.CartItem;
import com.example.cart.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public Collection<CartItem> getCart() {
        return cartService.getCartItems();
    }

    @PostMapping
    public void addToCart(@RequestBody CartItem item) {
        cartService.addToCart(item);
    }

    @PutMapping("/{id}")
    public void updateQty(@PathVariable Long id, @RequestParam int qty) {
        cartService.updateQuantity(id, qty);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        cartService.removeItem(id);
    }
}
