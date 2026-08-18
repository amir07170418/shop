package org.example.shop.dto;

import jakarta.validation.constraints.NotNull;

public class OrderItemRequest {
    @NotNull
    private Integer quantity;
    @NotNull
    private Long orderId;
    @NotNull
    private Long productId;

    public OrderItemRequest(Integer quantity, Long orderId, Long productId) {
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
    }

    public OrderItemRequest() {
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "OrderItemRequest{" +
                "quantity=" + quantity +
                ", orderId=" + orderId +
                ", productId=" + productId +
                '}';
    }
}
