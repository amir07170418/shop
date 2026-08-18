package org.example.shop.dto;

import org.example.shop.model.PaymentStatus;

import java.time.LocalDateTime;

public class PaymentResponse {
    private Long id;
    private Long amount;
    private LocalDateTime paymentDate;
    private PaymentStatus status;
    private Long orderId;

    public PaymentResponse(Long id, Long amount, LocalDateTime paymentDate, PaymentStatus status, Long orderId) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
        this.orderId = orderId;
    }
    public PaymentResponse() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "PaymentResponse{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", status=" + status +
                ", orderId=" + orderId +
                '}';
    }
}
