package org.example.shop.dto;

import jakarta.validation.constraints.NotNull;

public class CartItemRequest {
    @NotNull
    private Integer quantity;
    @NotNull
    private  Long productId;

    public CartItemRequest(Integer quantity, Long productId) {
        this.quantity = quantity;
        this.productId = productId;
    }

    public CartItemRequest() {}

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
                ", productId=" + productId +
                '}';
    }
}
