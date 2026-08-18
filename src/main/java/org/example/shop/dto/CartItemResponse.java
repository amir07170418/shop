package org.example.shop.dto;

public class CartItemResponse {
    private Long id;
    private Integer quantity;
    private Long cartId;
    private  Long productId;

    public CartItemResponse(Long id, Integer quantity, Long cartId, Long productId) {
        this.id = id;
        this.quantity = quantity;
        this.cartId = cartId;
        this.productId = productId;
    }

    public CartItemResponse() {
    }

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
        return "CartItemResponse{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", cartId=" + cartId +
                ", productId=" + productId +
                '}';
    }
}
