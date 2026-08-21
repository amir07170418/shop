package org.example.shop.controller;

import org.example.shop.dto.CartResponse;
import org.example.shop.service.CartService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public CartResponse findById(@PathVariable Long id) {
        return cartService.findById(id);
    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping
    public CartResponse getCart() {
        return cartService.getCart();
    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping
    public void clearCart() {
        cartService.clearCart();
    }
}
