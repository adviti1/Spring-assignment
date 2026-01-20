package com.example.demo.controller;

import com.example.demo.model.CartItem;
import com.example.demo.service.CartService;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    // GET CART
    @GetMapping
    public List<CartItem> getCart() {
        return cartService.getCartItems();
    }

    // ADD TO CART
    @PostMapping("/add/{id}")
    public List<CartItem> addToCart(@PathVariable Long id) {
        cartService.addToCart(id);
        return cartService.getCartItems();
    }

    // UPDATE QTY
    @PutMapping("/update/{id}")
    public List<CartItem> updateQty(@PathVariable Long id,
                                    @RequestParam int qty) {
        cartService.updateQuantity(id, qty);
        return cartService.getCartItems();
    }

    // DELETE ITEM
    @DeleteMapping("/delete/{id}")
    public List<CartItem> deleteItem(@PathVariable Long id) {
        cartService.deleteItem(id);
        return cartService.getCartItems();
    }

    // CHECKOUT
    @PostMapping("/checkout")
    public List<CartItem> checkout() {
        cartService.checkout();
        return List.of();
    }
}
