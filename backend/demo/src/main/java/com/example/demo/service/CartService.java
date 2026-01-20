package com.example.demo.service;

import com.example.demo.model.CartItem;
import com.example.demo.model.Product;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public CartService(CartItemRepository cartItemRepository,
                       ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    // GET CART
    public List<CartItem> getCartItems() {
        return cartItemRepository.findAll();
    }

    // ADD TO CART
    public void addToCart(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem item = cartItemRepository.findByProduct(product)
                .orElse(new CartItem(product, 0));

        item.setQuantity(item.getQuantity() + 1);
        cartItemRepository.save(item);
    }

    // UPDATE QTY
    public void updateQuantity(Long cartItemId, int qty) {
        CartItem item = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        item.setQuantity(qty);
        cartItemRepository.save(item);
    }

    // DELETE ITEM
    public void deleteItem(Long cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    // CHECKOUT
    public void checkout() {
        cartItemRepository.deleteAll();
    }
}
