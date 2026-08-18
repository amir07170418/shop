package org.example.shop.dto;


public class OrderItemResponse {
    private Long id;
    private Integer quantity;
    private Long totalPrice;
    private Long unitPrice;
    private Long orderId;
    private Long productId;

    public OrderItemResponse(Long id, Integer quantity, Long totalPrice, Long unitPrice, Long orderId, Long productId) {
        this.id = id;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.unitPrice = unitPrice;
        this.orderId = orderId;
        this.productId = productId;
    }
    public OrderItemResponse() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Long unitPrice) {
        this.unitPrice = unitPrice;
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
        return "OrderItemResponse{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", unitPrice=" + unitPrice +
                ", orderId=" + orderId +
                ", productId=" + productId +
                '}';
    }
}
