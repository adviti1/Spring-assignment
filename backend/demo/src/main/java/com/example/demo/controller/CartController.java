package com.example.demo.controller;

import com.example.demo.model.CartItem;
import com.example.demo.service.CartService;
import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.CartItemDTO;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
public List<CartItemDTO> getCart() {
    return cartService.getCartItems();
}

@PostMapping("/add/{id}")
public List<CartItemDTO> addToCart(@PathVariable Long id) {
    cartService.addToCart(id);
    return cartService.getCartItems();
}

@PutMapping("/update/{id}")
public List<CartItemDTO> updateQty(@PathVariable Long id,
                                   @RequestParam int qty) {
    cartService.updateQuantity(id, qty);
    return cartService.getCartItems();
}

@DeleteMapping("/delete/{id}")
public List<CartItemDTO> deleteItem(@PathVariable Long id) {
    cartService.deleteItem(id);
    return cartService.getCartItems();
}

@PostMapping("/checkout")
public List<CartItemDTO> checkout() {
    cartService.checkout();
    return List.of();
}


}
