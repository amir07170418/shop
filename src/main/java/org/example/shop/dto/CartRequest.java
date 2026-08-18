package org.example.shop.dto;

import jakarta.validation.constraints.NotNull;

public class CartRequest {
    @NotNull
    private Long CustomerId;

    public CartRequest(Long customerId) {
        CustomerId = customerId;
    }

    public CartRequest() {
    }

    public Long getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(Long customerId) {
        CustomerId = customerId;
    }

    @Override
    public String toString() {
        return "CartRequest{" +
                "CustomerId=" + CustomerId +
                '}';
    }
}
