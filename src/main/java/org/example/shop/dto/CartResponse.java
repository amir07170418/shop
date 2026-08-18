package org.example.shop.dto;

import jakarta.validation.constraints.NotNull;

public class CartResponse {
    private Long customerId;
    private Long id;
    private Long totalPrice;

    public CartResponse(Long customerId, Long id, Long totalPrice) {
        this.customerId = customerId;
        this.id = id;
        this.totalPrice = totalPrice;
    }
    public CartResponse() {}

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "CartResponse{" +
                "customerId=" + customerId +
                ", id=" + id +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
