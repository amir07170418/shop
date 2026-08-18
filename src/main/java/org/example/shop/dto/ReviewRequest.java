package org.example.shop.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewRequest {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private Long productId;
    @NotNull
    private Long customerId;

    public ReviewRequest(String title, String description, Long productId, Long customerId) {
        this.title = title;
        this.description = description;
        this.productId = productId;
        this.customerId = customerId;
    }
    public ReviewRequest() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "ReviewRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", productId=" + productId +
                ", customerId=" + customerId +
                '}';
    }
}
