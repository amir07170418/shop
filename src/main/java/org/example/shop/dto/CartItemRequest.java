package org.example.shop.dto;

import jakarta.validation.constraints.NotNull;

public class CartItemRequest {
    @NotNull
    private Integer quantity;
    @NotNull
    private Long cartId;
    @NotNull
    private  Long productId;

    public CartItemRequest(Integer quantity, Long cartId, Long productId) {
        this.quantity = quantity;
        this.cartId = cartId;
        this.productId = productId;
    }

    public CartItemRequest() {}

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "CartItemRequest{" +
                "quantity=" + quantity +
                ", cartId=" + cartId +
                ", productId=" + productId +
                '}';
    }
}
