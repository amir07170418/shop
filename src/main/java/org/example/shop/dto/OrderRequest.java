package org.example.shop.dto;

import jakarta.validation.constraints.NotNull;

public class OrderRequest {
    @NotNull
    private Long customerId;

    public OrderRequest(Long customerId) {
        this.customerId = customerId;
    }
    public OrderRequest() {}

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "customerId=" + customerId +
                '}';
    }
}
