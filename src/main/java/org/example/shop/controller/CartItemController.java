package org.example.shop.controller;

import org.example.shop.dto.CartItemRequest;
import org.example.shop.dto.CartItemResponse;
import org.example.shop.service.CartItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cartItems")
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping("/add")
    public CartItemResponse add(@RequestBody CartItemRequest cartItemRequest) {
        return cartItemService.addCartItem(cartItemRequest);
    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        cartItemService.deleteCartItem(id);
    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/{id}")
    public CartItemResponse findById(@PathVariable Long id) {
        return cartItemService.getById(id);
    }
    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping
    public List<CartItemResponse> findAll() {
        return cartItemService.getCartItems();
    }



}
