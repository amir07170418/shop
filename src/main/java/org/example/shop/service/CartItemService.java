package org.example.shop.service;

import jakarta.transaction.Transactional;
import org.example.shop.dto.CartItemRequest;
import org.example.shop.dto.CartItemResponse;
import org.example.shop.exception.ShopException;
import org.example.shop.mapper.CartItemMapper;
import org.example.shop.model.CartItem;
import org.example.shop.model.Customer;
import org.example.shop.model.Product;
import org.example.shop.repository.CartItemRepository;
import org.example.shop.repository.CustomerRepository;
import org.example.shop.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CartItemService {
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public CartItemService(CartItemRepository cartItemRepository, CartItemMapper cartItemMapper, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.cartItemMapper = cartItemMapper;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }
    @Transactional
    public CartItemResponse addCartItem(CartItemRequest cartItemRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                () -> new ShopException("customer not found", HttpStatus.NOT_FOUND));
        Product product = productRepository.findById(cartItemRequest.getProductId()).orElseThrow
                (() -> new ShopException("product not found", HttpStatus.NOT_FOUND));
        CartItem cartItem = cartItemRepository.findCartItemByCartIdAndProductId
                (customer.getCart().getId(), cartItemRequest.getProductId()).orElse(null);
        if (cartItem != null) {
            if (cartItemRequest.getQuantity()+cartItem.getQuantity()<= product.getStock()) {
                cartItem.setQuantity(cartItem.getQuantity() + cartItemRequest.getQuantity());
                cartItemRepository.save(cartItem);
                return cartItemMapper.toCartItemResponse(cartItem);
            } else {
                throw new ShopException("stock is less than Quantity", HttpStatus.BAD_REQUEST);
            }
        } else {
            CartItem newCartItem = cartItemMapper.toCartItem(cartItemRequest);
            newCartItem.setCart(customer.getCart());
            newCartItem.setProduct(product);
            if (newCartItem.getQuantity() > newCartItem.getProduct().getStock()) {
                throw  new ShopException("stock is less than Quantity", HttpStatus.BAD_REQUEST);
            }
            cartItemRepository.save(newCartItem);
            return cartItemMapper.toCartItemResponse(newCartItem);
        }

    }
    @Transactional
    public void deleteCartItem(Long id) {
        CartItem cartItem = cartItemRepository.findById(id).orElseThrow(
                () -> new ShopException("cart item not found", HttpStatus.NOT_FOUND));
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.findByEmail(email).orElseThrow
                (() -> new ShopException("customer not found", HttpStatus.NOT_FOUND));
        if (!customer.getCart().getId().equals(cartItem.getCart().getId())) {
            throw  new ShopException("cart item is not yours", HttpStatus.BAD_REQUEST);
        }
        cartItemRepository.delete(cartItem);
    }
    public List<CartItemResponse> getCartItems(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                ()->new ShopException("Customer Not Found", HttpStatus.NOT_FOUND));
        return customer.getCart().getCartItems().stream().map(cartItemMapper::toCartItemResponse).toList();
    }

}
