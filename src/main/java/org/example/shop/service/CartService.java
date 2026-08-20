package org.example.shop.service;


import jakarta.transaction.Transactional;
import org.example.shop.dto.CartResponse;
import org.example.shop.exception.ShopException;
import org.example.shop.mapper.CartMapper;
import org.example.shop.model.Cart;
import org.example.shop.model.CartItem;
import org.example.shop.model.Customer;
import org.example.shop.repository.CartRepository;
import org.example.shop.repository.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final CustomerRepository customerRepository;

    public CartService(CartRepository cartRepository, CartMapper cartMapper, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.cartMapper = cartMapper;
        this.customerRepository = customerRepository;
    }
    public CartResponse findById(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(
                ()->new ShopException("Cart not Found", HttpStatus.NOT_FOUND));
        CartResponse cartResponse = cartMapper.toCartResponse(cart);
        cartResponse.setTotalPrice(calculateTotalPrice(cart));
        return cartResponse;
    }
    public CartResponse getCart() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                ()->new ShopException("Customer Not Found", HttpStatus.NOT_FOUND));
        CartResponse cartResponse = cartMapper.toCartResponse(customer.getCart());
        cartResponse.setTotalPrice(calculateTotalPrice(customer.getCart()));
        return cartResponse;
    }
    @Transactional
    public void clearCart(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                ()->new ShopException("Customer Not Found", HttpStatus.NOT_FOUND));
        cartRepository.deleteCartItems(customer.getCart().getId());
    }
    private Long calculateTotalPrice(Cart  cart) {
        Set<CartItem> cartItems = cart.getCartItems();
        long totalPrice = 0L;
        for (CartItem cartItem : cartItems) {
            totalPrice+=cartItem.getProduct().getPrice()*cartItem.getQuantity();
        }
        return totalPrice;
    }
}
