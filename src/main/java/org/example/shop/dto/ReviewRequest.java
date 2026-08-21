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

    public ReviewRequest(String title, String description, Long productId) {
        this.title = title;
        this.description = description;
        this.productId = productId;
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



    @Override
    public String toString() {
        return "ReviewRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", productId=" + productId +
                '}';
    }
}
