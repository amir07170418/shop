package org.example.shop.dto;


public class ReviewResponse {
    private Long id;
    private String title;
    private String description;
    private Long productId;
    private Long customerId;

    public ReviewResponse(Long id, String title, String description, Long productId, Long customerId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.productId = productId;
        this.customerId = customerId;
    }
    public ReviewResponse() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return "ReviewResponse{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", productId=" + productId +
                ", customerId=" + customerId +
                '}';
    }
}
