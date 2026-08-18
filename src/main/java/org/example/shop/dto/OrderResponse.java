package org.example.shop.dto;


import org.example.shop.model.OrderStatus;
import org.example.shop.model.PaymentStatus;

import java.time.LocalDateTime;

public class OrderResponse {
    private Long id;
    private Long finalPrice;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private Long customerId;
    private PaymentStatus paymentStatus;
    private Long couponCode;

    public OrderResponse(Long id, Long finalPrice, LocalDateTime orderDate, OrderStatus orderStatus, Long customerId
            , PaymentStatus paymentStatus, Long couponCode) {
        this.id = id;
        this.finalPrice = finalPrice;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.customerId = customerId;
        this.paymentStatus = paymentStatus;
        this.couponCode = couponCode;
    }

    public OrderResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Long finalPrice) {
        this.finalPrice = finalPrice;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Long getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(Long couponCode) {
        this.couponCode = couponCode;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "id=" + id +
                ", finalPrice=" + finalPrice +
                ", orderDate=" + orderDate +
                ", orderStatus=" + orderStatus +
                ", customerId=" + customerId +
                ", paymentStatus=" + paymentStatus +
                ", couponCode=" + couponCode +
                '}';
    }

}
